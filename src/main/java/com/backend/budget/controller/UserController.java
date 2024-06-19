package com.backend.budget.controller;

import com.backend.budget.models.UserModel;
import com.backend.budget.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/readUsers")
    public List<UserModel> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/saverUser")
    public String saveUser(@RequestBody UserModel userModel) {
        userService.saveUser(userModel);
        return "User save succesfully";
   }

}