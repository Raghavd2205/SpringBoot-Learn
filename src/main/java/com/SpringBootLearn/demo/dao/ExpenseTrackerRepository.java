package com.SpringBootLearn.demo.dao;

import com.SpringBootLearn.demo.entity.ExpenseTracker;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ExpenseTrackerRepository  extends JpaRepository<ExpenseTracker, Integer>{
    @Query("""
    SELECT e FROM ExpenseTracker e
    WHERE (:category IS NULL OR e.category = :category)
    AND (:minAmount IS NULL OR e.amount >= :minAmount)
    AND (:maxAmount IS NULL OR e.amount <= :maxAmount)
""")
    Page<ExpenseTracker> filterExpenses(
            String category,
            Integer minAmount,
            Integer maxAmount,
            Pageable pageable
    );
}
