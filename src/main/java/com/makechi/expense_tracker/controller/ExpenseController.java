package com.makechi.expense_tracker.controller;

import com.makechi.expense_tracker.dto.expense.ExpenseDto;
import com.makechi.expense_tracker.entity.Category;
import com.makechi.expense_tracker.entity.User;
import com.makechi.expense_tracker.service.expense.ExpenseService;
import com.makechi.expense_tracker.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/expenses")
@RequiredArgsConstructor
public class ExpenseController {

    private final ExpenseService expenseService;
    private final UserService userService;

    @GetMapping
    public List<ExpenseDto> getAllExpenses(@AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.findByUsername(userDetails.getUsername());
        return expenseService.getAllExpenses(user);
    }

    @PostMapping
    public ExpenseDto saveExpense(
            @RequestBody AddUpdateExpenseRequest request,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        User user = userService.findByUsername(userDetails.getUsername());
        return expenseService.addExpense(user, request);
    }

    @GetMapping("/{expenseID}")
    public ExpenseDto getExpenseById(@PathVariable long expenseID) {
        return expenseService.getExpenseById(expenseID);
    }

    @PutMapping("/{expenseID}")
    public ExpenseDto updateExpense(@PathVariable long expenseID, @RequestBody AddUpdateExpenseRequest request) {
        return expenseService.updateExpense(expenseID, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteExpenseById(@PathVariable("id") long expenseID) {
        boolean deleted = expenseService.deleteExpenseById(expenseID);
        Map<Boolean, String> response = new HashMap<>();
        response.put(deleted, "Expense with id " + expenseID + " deleted successfully");
        return ResponseEntity.ok()
                .body(response);
    }

    @GetMapping("/category/{category}")
    public List<ExpenseDto> filterExpensesByCategory(
            @PathVariable Category category,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        User user = userService.findByUsername(userDetails.getUsername());
        return expenseService.filterExpenseByCategory(user, category);
    }

    @GetMapping("/date")
    public List<ExpenseDto> filterExpensesByTime(
            @RequestParam("filter") String filter,
            @RequestParam(value = "start", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate start,
            @RequestParam(value = "end", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        User user = userService.findByUsername(userDetails.getUsername());
        LocalDate now = LocalDate.now();
        LocalDate fromDate;

        switch (filter) {
            case "past-week" -> fromDate = now.minusWeeks(1);
            case "past-month" -> fromDate = now.minusMonths(1);
            case "last-3-months" -> fromDate = now.minusMonths(3);
            case "custom" -> {
                if (start == null || end == null) {
                    throw new IllegalArgumentException("For custom date range, start and end dates must be provided.");
                }
                fromDate = start;
                now = end;
            }
            default -> throw new IllegalArgumentException("Invalid filter type");
        }

        return expenseService.filterExpensesByTime(user, fromDate, now);
    }
}
