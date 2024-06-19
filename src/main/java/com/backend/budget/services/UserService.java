package com.backend.budget.services;

import com.backend.budget.models.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void saveUser(UserModel userModel) {
        try {
            jdbcTemplate.execute((Connection conn) -> {
                CallableStatement callableStatement = conn.prepareCall("CALL SAVE_USERS(?, ?, ?, ?, ?, ?)");
                callableStatement.setString(1, userModel.getFirstname());
                callableStatement.setString(2, userModel.getLastname());
                callableStatement.setString(3, userModel.getUsername());
                callableStatement.setString(4, userModel.getEmail());
                callableStatement.setString(5, userModel.getPhone());
                callableStatement.setString(6, userModel.getPassword());
                callableStatement.execute();
                return null;
            });
        } catch (Exception e) {
            System.out.println("Error saving user data: " + e.getMessage());
        }
    }

    public List<UserModel> getAllUsers() {
        String sql = "SELECT user_id, username, firstname, lastname, email, phone, password, created_at, update_at FROM users";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(UserModel.class));
    }
}