package com.backend.budget.services;

import com.backend.budget.models.ItemsModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.CallableStatement;
import java.sql.Connection;

@Service
public class ItemsService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void saveItem(ItemsModel itemsModel) {
        try {
            jdbcTemplate.execute((Connection conn) -> {
                CallableStatement callableStatement = conn.prepareCall("CALL SAVE_ITEM(?, ?, ?, ?, ?)");
                callableStatement.setInt(1, itemsModel.getItem_id());
                callableStatement.setInt(2, itemsModel.getQuantity());
                callableStatement.setString(3, itemsModel.getDescription());
                callableStatement.setInt(4, itemsModel.getUnit_price());
                callableStatement.setInt(5, itemsModel.getTotal_price());
                callableStatement.execute();
                return null;
            });
        } catch (Exception e) {
            System.out.println("Error saving items: " + e.getMessage());
        }
    }

    // Save item by budget
    public void saveBudgetItem(ItemsModel itemsModel) {
        try {
            jdbcTemplate.execute((Connection conn) -> {
                CallableStatement callableStatement = conn.prepareCall("CALL SAVE_BUDGET_CAT(?, ?, ?)");
                callableStatement.setInt(1, itemsModel.getBudget_id());
                callableStatement.setInt(2, itemsModel.getItem_id());
                callableStatement.setInt(3, itemsModel.getAmount());
                callableStatement.execute();
                return null;
            });
        } catch (Exception e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }
}