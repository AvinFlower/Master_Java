package com.example.lr15.entities;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
@Table(name = "orderslist")
public class OrdersList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "order_status")
    private String order_status;

    @Column(name = "order_date")
    private String order_date;

    @Column(name = "total_cost")
    private Double total_cost;


    public String getStatusClass() {
        String status = this.getOrder_status();
        if ("In Process".equals(status)) {
            return "status-in-process";
        }
        else if ("Completed".equals(status)) {
            return "status-completed";
        }
        else {
            return "";
        }
    }

}
