package com.backend.budget.models;

import com.backend.budget.models.enums.CustomerType;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CustomerModel {
    private Integer customer_id;
    private Integer user_id;
    private String customer_name;
    private String email;
    private String phone;
    private CustomerType customerType;
    private LocalDateTime created_at;
    private LocalDateTime update_at;
}