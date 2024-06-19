package com.backend.budget.models;

import com.backend.budget.models.enums.BudgetStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BudgetModel {
    private Integer budget_id;
    private Integer customer_id;
    private Integer user_id;
    private String email;
    private String tittle;
    private String description;
    private String amount;
    private String currency;
    private BudgetStatus budgetStatus;
    private LocalDateTime created_at;
    private LocalDateTime update_at;
}