package com.backend.budget.controller;

import com.backend.budget.models.BudgetCategoriesModel;
import com.backend.budget.models.BudgetModel;
import com.backend.budget.services.BudgetCategoriesService;
import com.backend.budget.services.BudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.metadata.HsqlTableMetaDataProvider;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.ResponseErrorHandler;

import java.util.List;

@RestController
@RequestMapping("/budget")
public class BudgetController {
    @Autowired
    private BudgetService budgetService;

    @Autowired
    private BudgetCategoriesService budgetCategoriesService;

    @GetMapping("/readBudget")
    public List<BudgetModel> getAllBudgets(){
        return budgetService.getAllBudgets();
    }

    @GetMapping("/readCategories")
    public List<BudgetCategoriesModel> getAllCategories(){
        return budgetCategoriesService.getAllCategories();
    }

    @PostMapping("/saveBudget")
    public ResponseEntity<String> saveBudget(@RequestBody BudgetModel budgetModel) {
        try {
            budgetService.saveBudget(budgetModel);
            return ResponseEntity.ok("Budget data save succesfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving budget: " + e.getMessage());
        }
    }
    @PostMapping("/saveCategories")
    public ResponseEntity<String> saveCategorie(@RequestBody BudgetCategoriesModel budgetCategoriesModel) {
        try {
            budgetCategoriesService.saveCategories(budgetCategoriesModel);
            return ResponseEntity.ok("Budget categories data save succesfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving data: " + e.getMessage());
        }
    }
}