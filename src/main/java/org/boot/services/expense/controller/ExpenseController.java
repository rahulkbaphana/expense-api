package org.boot.services.expense.controller;

import org.boot.services.expense.model.Expense;
import org.boot.services.expense.model.ExpenseView;
import org.boot.services.expense.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.ValidationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@RestController
public class ExpenseController {

    private ExpenseRepository repository;

    @Autowired
    public ExpenseController(ExpenseRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/expense")
    public Map<String,String> saveExpense(@RequestBody @Valid Expense expense, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException("Validation failed in saving expense. Amount is mandatory field.");
        }
        Expense savedMetadata = repository.save(expense);
        return new HashMap<String,String>(){{
            put("id",savedMetadata.getId().toString());
            put("message","Successfully saved expense.");
        }};
    }

    @GetMapping("/expenses")
    public List<ExpenseView> getAllExpense() {
        List<Expense> allExpenses = repository.findAll();
        return allExpenses.stream().map(ExpenseView::new).collect(toList());
    }
}
