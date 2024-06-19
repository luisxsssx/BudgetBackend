package com.backend.budget.controller;

import com.backend.budget.models.BudgetModel;
import com.backend.budget.services.BudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("budget")
public class BudgetController {
    @Autowired
    private BudgetService budgetService;

    @GetMapping("readBudget")
    public List<BudgetModel> getAllBudgets(){
        return budgetService.getAllBudgets();
    }
}