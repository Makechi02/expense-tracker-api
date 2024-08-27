package com.makechi.expense_tracker.dto.expense;

import com.makechi.expense_tracker.entity.Expense;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class ExpenseDtoMapper implements Function<Expense, ExpenseDto> {
    @Override
    public ExpenseDto apply(Expense expense) {
        return ExpenseDto.builder()
                .id(expense.getId())
                .description(expense.getDescription())
                .category(expense.getCategory())
                .amount(expense.getAmount())
                .transactionDate(expense.getTransactionDate())
                .build();
    }
}
