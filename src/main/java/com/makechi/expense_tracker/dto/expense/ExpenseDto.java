package com.makechi.expense_tracker.dto.expense;

import com.makechi.expense_tracker.entity.Category;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class ExpenseDto {
    private Long id;
    private String description;
    private Category category;
    private double amount;
    private LocalDate transactionDate;
}
