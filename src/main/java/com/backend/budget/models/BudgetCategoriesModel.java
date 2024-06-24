package com.backend.budget.models;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BudgetCategoriesModel {
    private Integer categories_id;
    private String name;
    private String description;
    private LocalDateTime created_at;
    private LocalDateTime update_at;
}