package com.makechi.expense_tracker.service.expense;

import com.makechi.expense_tracker.controller.AddUpdateExpenseRequest;
import com.makechi.expense_tracker.dto.expense.ExpenseDto;
import com.makechi.expense_tracker.entity.Category;
import com.makechi.expense_tracker.entity.User;

import java.time.LocalDate;
import java.util.List;

public interface ExpenseService {
    List<ExpenseDto> getAllExpenses(User user);
    ExpenseDto addExpense(User user, AddUpdateExpenseRequest request);
    ExpenseDto getExpenseById(long id);
    ExpenseDto updateExpense(long id, AddUpdateExpenseRequest request);
    boolean deleteExpenseById(long id);
    List<ExpenseDto> filterExpenseByCategory(User user, Category category);
    List<ExpenseDto> filterExpensesByTime(User user, LocalDate fromDate, LocalDate now);
}
