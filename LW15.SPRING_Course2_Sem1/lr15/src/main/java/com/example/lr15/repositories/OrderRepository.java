package com.example.lr15.repositories;

import com.example.lr15.entities.OrdersList;
import com.example.lr15.entities.OrdersList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;



@Repository
public interface OrderRepository extends JpaRepository<OrdersList,Integer>, JpaSpecificationExecutor<OrdersList> {
}
