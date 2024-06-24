package com.backend.budget.models;

import com.backend.budget.models.enums.Status;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BudgetModel {
    private Integer budget_id;
    private Integer customer_id;
    private Integer user_id;
    private String tittle;
    private String description;
    private Integer amount;
    private String currency;
    private Status status;
    private Integer categorie_id;
    private LocalDateTime created_at;
    private LocalDateTime update_at;
}