package com.makechi.expense_tracker.service.expense;

import com.makechi.expense_tracker.controller.AddUpdateExpenseRequest;
import com.makechi.expense_tracker.dto.expense.ExpenseDto;
import com.makechi.expense_tracker.dto.expense.ExpenseDtoMapper;
import com.makechi.expense_tracker.entity.Category;
import com.makechi.expense_tracker.entity.Expense;
import com.makechi.expense_tracker.entity.User;
import com.makechi.expense_tracker.exception.RequestValidationException;
import com.makechi.expense_tracker.exception.ResourceNotFoundException;
import com.makechi.expense_tracker.repository.ExpenseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final ExpenseDtoMapper expenseDtoMapper;

    @Override
    public List<ExpenseDto> getAllExpenses(User user) {
        return expenseRepository.findAllByUser(user).stream().map(expenseDtoMapper).toList();
    }

    @Override
    public ExpenseDto addExpense(User user, AddUpdateExpenseRequest request) {

        if (request.description().isBlank()) {
            throw new RuntimeException("Expense description is blank");
        }

        if (request.amount() <= 0) {
            throw new RuntimeException("Amount can't be less or equal to zero");
        }

        if (request.category() == null) {
            throw new RuntimeException("Category can't be blank");
        }

        var expense = Expense.builder()
                .description(request.description())
                .category(request.category())
                .amount(request.amount())
                .user(user)
                .build();

        expense = expenseRepository.save(expense);
        return expenseDtoMapper.apply(expense);
    }

    @Override
    public ExpenseDto getExpenseById(long id) {
        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Expense with id " + id + " not found"));
        return expenseDtoMapper.apply(expense);
    }

    @Override
    public ExpenseDto updateExpense(long id, AddUpdateExpenseRequest request) {
        Expense fetchedExpense = expenseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Expense with id " + id + " not found"));

        boolean changes = false;

        if (!request.description().isBlank() && !request.description().equals(fetchedExpense.getDescription())) {
            fetchedExpense.setDescription(request.description());
            changes = true;
        }

        if (request.category() != null && !request.category().equals(fetchedExpense.getCategory())) {
            fetchedExpense.setCategory(request.category());
            changes = true;
        }

        if (request.amount() != fetchedExpense.getAmount()) {
            fetchedExpense.setAmount(request.amount());
            changes = true;
        }

        if (!changes) {
            throw new RequestValidationException("No data changes");
        }

        var expense = expenseRepository.save(fetchedExpense);
        return expenseDtoMapper.apply(expense);
    }

    @Override
    public boolean deleteExpenseById(long id) {
        if (!expenseRepository.existsById(id)) {
            throw new ResourceNotFoundException("Expense with id " + id + " not found");
        }

        expenseRepository.deleteById(id);
        log.info("Expense with id deleted: {}", id);
        return true;
    }

    @Override
    public List<ExpenseDto> filterExpenseByCategory(User user, Category category) {
        return expenseRepository.findByUserAndCategory(user, category).stream().map(expenseDtoMapper).toList();
    }

    @Override
    public List<ExpenseDto> filterExpensesByTime(User user, LocalDate fromDate, LocalDate now) {
        return expenseRepository.findByUserAndTransactionDateBetween(user, fromDate, now).stream().map(expenseDtoMapper).toList();
    }
}
