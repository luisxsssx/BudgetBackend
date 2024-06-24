package com.backend.budget.controller;

import com.backend.budget.models.UserModel;
import com.backend.budget.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
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

    @PostMapping("/saveUser")
    public ResponseEntity<String> saveUser(@RequestBody UserModel userModel) {
        try {
            userService.saveUser(userModel);
            return ResponseEntity.ok("User data save succesfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving user: " + e.getMessage());
        }
   }

   @PostMapping("/deleteUser/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Integer user_id) {
        try {
            userService.deleteUser(user_id);
            return ResponseEntity.ok("User deleted succesfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting user: " + e.getMessage());
        }
   }

}