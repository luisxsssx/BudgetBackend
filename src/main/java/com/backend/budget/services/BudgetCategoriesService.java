package com.backend.budget.services;

import com.backend.budget.models.BudgetCategoriesModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.util.List;

@Service
public class BudgetCategoriesService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void saveCategories(BudgetCategoriesModel budgetCategoriesModel) {
        try {
            jdbcTemplate.execute((Connection conn) -> {
                CallableStatement callableStatement = conn.prepareCall("CALL SAVE_BUDGET_CAT(?, ?)");
                callableStatement.setString(1, budgetCategoriesModel.getName());
                callableStatement.setString(2, budgetCategoriesModel.getDescription());
                callableStatement.execute();
                return null;
            });
        } catch (Exception e) {
            System.out.println("Error saving customer data: " + e.getMessage());
        }
    }

    public List<BudgetCategoriesModel> getAllCategories(){
        String sql = "SELECT categories_id, name, description, created_at, update_at FROM BUDGET_CATEGORIES";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(BudgetCategoriesModel.class));
    }

}