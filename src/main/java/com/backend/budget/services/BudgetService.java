package com.backend.budget.services;

import com.backend.budget.models.BudgetModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.util.List;

@Service
public class BudgetService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void saveBudget(BudgetModel budgetModel) {
        try {
            jdbcTemplate.execute((Connection conn) -> {
                CallableStatement callableStatement = conn.prepareCall("CALL SAVE_BUDGET(?, ?, ?, ?, ?, ?, ?, ?)");
                callableStatement.setInt(1, budgetModel.getUser_id());
                callableStatement.setInt(2, budgetModel.getCustomer_id());
                callableStatement.setString(3, budgetModel.getTittle());
                callableStatement.setString(4, budgetModel.getDescription());
                callableStatement.setInt(5, budgetModel.getAmount());
                callableStatement.setString(6, budgetModel.getCurrency());
                callableStatement.setInt(7, budgetModel.getCategorie_id());
                callableStatement.setString(8, budgetModel.getStatus().name());
                callableStatement.execute();
                return null;
            });
        } catch (Exception e) {
            System.out.println("Error saving budget data: " + e.getMessage());
        }
    }

    public void updateBudget(BudgetModel budgetModel) {
        try {
            jdbcTemplate.execute((Connection conn) -> {
                CallableStatement callableStatement = conn.prepareCall("CALL UPDATE_BUDGET(?, ?, ?, ?, ?, ?, ?, ?)");
                callableStatement.setInt(1, budgetModel.getBudget_id());
                callableStatement.setInt(1, budgetModel.getUser_id());
                callableStatement.setInt(2, budgetModel.getCustomer_id());
                callableStatement.setString(3, budgetModel.getTittle());
                callableStatement.setString(4, budgetModel.getDescription());
                callableStatement.setInt(5, budgetModel.getAmount());
                callableStatement.setString(6, budgetModel.getCurrency());
                callableStatement.setInt(7, budgetModel.getCategorie_id());
                callableStatement.setString(8, budgetModel.getStatus().name());
                callableStatement.execute();
                return null;
            });
        } catch (Exception e) {
            System.out.println("Error updating data: " + e.getMessage());
        }
    }

    public List<BudgetModel> getAllBudgets(){
        String sql = "SELECT budget_id, user_id, customer_id, tittle, description, amount, currency, status, created_at, update_at, categorie_id FROM BUDGET";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(BudgetModel.class));
    }

}