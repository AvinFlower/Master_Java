package com.example.lr15.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.ui.Model;
import com.example.lr15.entities.OrdersList;
import com.example.lr15.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class OrderController {
    private OrderService orderService;
    private UserDetailsService userDetailsService;

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("")
    public String showOrdersList(Model model, @RequestParam(defaultValue = "0") int page) {
        Pageable pageable = PageRequest.of(page, 5);
        Page<OrdersList> orderPage = orderService.getAllOrders(pageable);
        model.addAttribute("orders", orderPage.getContent());
        model.addAttribute("order", new OrdersList());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", orderPage.getTotalPages());

        List<String> mostCommonOrderDate = calculateMostCommonOrderDate();
        model.addAttribute("mostCommonOrderDate", mostCommonOrderDate);

        return "orders";
    }

    @PostMapping("/orders/addOrUpdate/add")
    public String addOrder(@ModelAttribute(value = "ordersList") OrdersList ordersList) {
        orderService.add(ordersList);
        return "redirect:/";
    }

    @GetMapping("/orders/addOrUpdate/add")
    public String test(Model model) {
        Page<OrdersList> orderPage = orderService.getAllOrders(PageRequest.of(0, 5));
        model.addAttribute("orders", orderPage.getContent());
        model.addAttribute("order", new OrdersList());
        return "addOrUpdate";
    }

    @GetMapping("/orders/addOrUpdate/edit/{id}")
    public String editOrder(Model model, @PathVariable(value = "id") Integer id) {
        OrdersList ordersList = orderService.getById(id);
        model.addAttribute("order", ordersList);
        return "addOrUpdate";
    }

    @PostMapping("/orders/addOrUpdate/edit/update")
    public String updateOrder(@ModelAttribute(value = "order") OrdersList updatedOrganization) {
        OrdersList ordersList = orderService.getById(updatedOrganization.getId());
        orderService.update(ordersList, updatedOrganization);
        return "redirect:/";
    }

    @GetMapping("/orders/show/{id}")
    public String showOneOrder(Model model, @PathVariable(value = "id") Integer id) {
        OrdersList ordersList = orderService.getById(id);
        model.addAttribute("order", ordersList);
        return "order-info";
    }

    @GetMapping("/orders/delete/{id}")
    public String deleteOrders(@PathVariable(value = "id") Integer id) {
        OrdersList ordersList = orderService.getById(id);
        orderService.delete(ordersList);
        return "redirect:/";
    }

    @GetMapping("/orders/filter")
    public String filterOrders(Model model,
                                @RequestParam(value = "order_status", required = false) String order_status,
                                @RequestParam(value = "order_date", required = false) String order_date,
                                @RequestParam(value = "total_cost", required = false) Double total_cost,
                                @RequestParam(defaultValue = "0") int page) {
        OrdersList ordersList = new OrdersList();

        Pageable pageable = PageRequest.of(page, 5);
        Page<OrdersList> orderPage = orderService.getAllOrders(order_status, order_date, total_cost, pageable);

        model.addAttribute("orders", orderPage.getContent());
        model.addAttribute("order", ordersList);
        model.addAttribute("order_status", order_status);
        model.addAttribute("order_date", order_date);
        model.addAttribute("total_cost", total_cost);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", orderPage.getTotalPages());


        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString("/orders/filter");
        if (order_status != null && !order_status.isEmpty()) uriBuilder.queryParam("order_status", order_status);
        if (order_date != null && !order_date.isEmpty()) uriBuilder.queryParam("order_date", order_date);
        if (total_cost != null) uriBuilder.queryParam("total_cost", total_cost);
        model.addAttribute("filterUrl", uriBuilder.build().toString());

        List<String> mostCommonOrderDate = calculateMostCommonOrderDate();
        model.addAttribute("mostCommonOrderDate", mostCommonOrderDate);

        return "orders";
    }

    @PostMapping("/authenticateTheUser")
    public String authenticateUser(@RequestParam("username") String username, @RequestParam("password") String password, Model model) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if (userDetails != null) {
            String storedPassword = userDetails.getPassword();
            if (password.equals(storedPassword)) {
                model.addAttribute("username", username);
                return "redirect:/orders";
            }
        }
        return "orders";
    }

    private List<String> calculateMostCommonOrderDate() {
        List<OrdersList> allOrders = orderService.getAllOrders();

        // Группировка и подсчет количества
        Map<String, Long> orderDateCountMap = allOrders.stream()
                .collect(Collectors.groupingBy(OrdersList::getOrder_date, Collectors.counting()));

        // Сортировка по убыванию количества
        List<String> mostCommonOrderDate = orderDateCountMap.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .limit(3)
                .map(entry -> entry.getKey() + " / " + entry.getValue() + " заказа(ов)")
                .collect(Collectors.toList());

        return mostCommonOrderDate.isEmpty() ? Collections.singletonList("Даты отсутствуют") : mostCommonOrderDate;
    }

}