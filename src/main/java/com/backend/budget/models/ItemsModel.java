package com.backend.budget.models;

import lombok.Data;

@Data
public class ItemsModel {
    private Integer budget_id;
    private Integer amount;
    private Integer item_id;
    private String description;
    private Integer quantity;
    private Integer unit_price;
    private Integer total_price;
}