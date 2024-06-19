package com.backend.budget.services;

import com.backend.budget.models.CustomerModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.CallableStatement;
import java.sql.Connection;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void saveCustomer(CustomerModel customerModel){
        try {
            jdbcTemplate.execute((Connection conn) -> {
                CallableStatement callableStatement = conn.prepareCall("call save_customers(?, ?, ?, ?, ?)");
                callableStatement.setInt(1, customerModel.getUser_id());
                callableStatement.setString(2, customerModel.getCustomer_name());
                callableStatement.setString(3, customerModel.getEmail());
                callableStatement.setString(4, customerModel.getPhone());
                callableStatement.setString(5, customerModel.getCustomerType().name());
                callableStatement.execute();
                return null;
            });
        } catch (Exception e) {
            System.out.println("Error saving customer data: " + e.getMessage());
        }
    }

    public List<CustomerModel> getAllCustomers(){
        String sql = "SELECT customer_id, user_id, email, phone, customer_type, created_at, update_at FROM customer;";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(CustomerModel.class));
    }

    public void updateCustomer(CustomerModel customerModel) {
        try {
            jdbcTemplate.execute((Connection conn) -> {
                CallableStatement callableStatement = conn.prepareCall("CALL UPDATE_CUSTOMER(?, ?, ?, ?, ?)");
                callableStatement.setInt(1, customerModel.getCustomer_id());
                callableStatement.setString(2, customerModel.getCustomer_name());
                callableStatement.setString(3, customerModel.getEmail());
                callableStatement.setString(4, customerModel.getPhone());
                return null;
            });
        } catch (Exception e) {
            System.out.println("Error updating customer data: " + e.getMessage());
        }
    }
}