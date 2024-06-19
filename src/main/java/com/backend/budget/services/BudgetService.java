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
                CallableStatement callableStatement = conn.prepareCall("call save_budget()");
                callableStatement.setInt(1, budgetModel.getUser_id());
                callableStatement.setInt(2, budgetModel.getCustomer_id());
                callableStatement.setString(3, budgetModel.getTittle());
                callableStatement.setString(4, budgetModel.getDescription());
                callableStatement.setString(5, budgetModel.getAmount());
                callableStatement.setString(6, budgetModel.getCurrency());
                callableStatement.setString(7, budgetModel.getBudgetStatus().name());
                callableStatement.execute();
                return null;
            });
        } catch (Exception e) {
            System.out.println("Erro saving budget data: " + e.getMessage());
        }
    }

    public List<BudgetModel> getAllBudgets(){
        String sql = "SELECT user_id, customer_id, tittle, description, amount, currency, status, created_at, update_at FROM BUDGET";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(BudgetModel.class));
    }

}