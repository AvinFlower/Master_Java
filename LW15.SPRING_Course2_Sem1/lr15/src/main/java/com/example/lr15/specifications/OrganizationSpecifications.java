package com.example.lr15.specifications;

import com.example.lr15.entities.OrdersList;
import org.springframework.data.jpa.domain.Specification;


public class OrganizationSpecifications {
    public static Specification<OrdersList> hasOrder_status(String order_status) {
        return ((root, query, criteriaBuilder) -> {
            if (order_status == null || order_status.isBlank()) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.like(
                    criteriaBuilder.lower(root.get("order_status")), "%" + order_status.toLowerCase() + "%");
        });
    }

    public static Specification<OrdersList> hasOrder_date(String order_date) {
        return ((root, query, criteriaBuilder) -> {
            if (order_date == null || order_date.isBlank()) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.like(
                    criteriaBuilder.lower(root.get("order_date")), "%" + order_date.toLowerCase() + "%");
        });
    }
    public static Specification<OrdersList> hasTotal_cost(Double total_cost) {
        return ((root, query, criteriaBuilder) -> {
            if (total_cost == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.lessThanOrEqualTo(root.get("total_cost").as(Double.class), total_cost);
        });
    }
}
