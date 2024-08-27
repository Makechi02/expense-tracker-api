package com.makechi.expense_tracker.repository;

import com.makechi.expense_tracker.entity.Category;
import com.makechi.expense_tracker.entity.Expense;
import com.makechi.expense_tracker.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    List<Expense> findByUserAndTransactionDateBetween(User user, LocalDate fromDate, LocalDate now);

    List<Expense> findByUserAndCategory(User user, Category category);

    List<Expense> findAllByUser(User user);
}
