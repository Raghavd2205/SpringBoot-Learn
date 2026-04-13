package com.SpringBootLearn.demo.service;
import com.SpringBootLearn.demo.dto.ExpenseTrackerRequestDTO;
import com.SpringBootLearn.demo.dto.ExpenseTrackerResponseDTO;
import com.SpringBootLearn.demo.entity.ExpenseTracker;

import java.util.List;

public interface ExpenseTrackerService {
    ExpenseTrackerResponseDTO addExpense(ExpenseTrackerRequestDTO expense);

    List<ExpenseTrackerResponseDTO> getAllExpenses();

    ExpenseTrackerResponseDTO getExpenseById(Integer id);

    ExpenseTrackerResponseDTO updateExpense(Integer id, ExpenseTrackerRequestDTO expense);

    String deleteExpense(Integer id);
}
