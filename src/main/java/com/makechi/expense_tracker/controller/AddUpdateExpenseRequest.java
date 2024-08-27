package com.makechi.expense_tracker.controller;

import com.makechi.expense_tracker.entity.Category;

public record AddUpdateExpenseRequest(
        String description,
        Category category,
        double amount
) {
}
