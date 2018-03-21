package org.boot.services.expense.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ExpenseView {
    private Expense expense;

    public ExpenseView(Expense expense) {
        this.expense = expense;
    }

    public String getId() {
        return expense.getId().toString();
    }

    public BigDecimal getAmount() {
        return expense.getAmount();
    }

    public String getCategory() {
        return expense.getCategory();
    }

    public LocalDateTime getDate() {
        return expense.getDate();
    }

    public String getUserId() {
        return expense.getUserId();
    }
}
