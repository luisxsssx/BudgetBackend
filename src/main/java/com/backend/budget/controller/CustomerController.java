package com.backend.budget.controller;

import com.backend.budget.models.CustomerModel;
import com.backend.budget.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/readCustomer")
    public List<CustomerModel> getAllCustomer(){
        return customerService.getAllCustomers();
    }

    @PostMapping("/saveCustomer")
    public ResponseEntity<String> saveCustomer(@RequestBody CustomerModel customerModel){
        try {
            customerService.saveCustomer(customerModel);
            return ResponseEntity.ok("Customer save succesfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving customer: " + e.getMessage());
        }
    }
}