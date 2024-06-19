package com.backend.budget.models;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserModel {
    private Integer user_id;
    private String firstname;
    private String lastname;
    private String username;
    private String email;
    private String phone;
    private String password;
    private LocalDateTime created_at;
    private LocalDateTime update_at;
}