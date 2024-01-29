package com.example.lr15.services;

import com.example.lr15.specifications.OrganizationSpecifications;
import com.example.lr15.entities.OrdersList;
import com.example.lr15.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    private final OrderRepository repository;

    @Autowired
    public OrderService(OrderRepository repository) {
        this.repository = repository;
    }

    public OrdersList getById(Integer id) {
        OrdersList ordersList = repository.findById(id).orElse(null);
        if(ordersList == null) throw new UsernameNotFoundException("");
        return ordersList;
    }

    public Page<OrdersList> getAllOrders(Pageable pageable) {
        return repository.findAll(pageable);
    }
    public Page<OrdersList> getAllOrders(String order_status, String order_date, Double total_cost, Pageable pageable) {
        Specification<OrdersList> specification = Specification
                .where(OrganizationSpecifications.hasOrder_status(order_status))
                .and(OrganizationSpecifications.hasOrder_date(order_date))
                .and(OrganizationSpecifications.hasTotal_cost(total_cost));
        return repository.findAll(specification, pageable);
    }

    public void add(OrdersList ordersList) {
        repository.save(ordersList);
    }

    public void delete(OrdersList ordersList) {
        repository.delete(ordersList);
    }

    public void update(OrdersList exist, OrdersList updated) {
        if (!updated.getOrder_status().isBlank()) exist.setOrder_status(updated.getOrder_status());
        if (!updated.getOrder_date().isBlank()) exist.setOrder_date(updated.getOrder_date());
        if (updated.getTotal_cost() != null) exist.setTotal_cost(updated.getTotal_cost());
        repository.save(exist);
    }
    public List<OrdersList> getAllOrders() {
        return repository.findAll();
    }
}
