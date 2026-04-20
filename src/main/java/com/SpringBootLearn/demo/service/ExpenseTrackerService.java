package com.SpringBootLearn.demo.service;
import com.SpringBootLearn.demo.dto.ExpenseTrackerRequestDTO;
import com.SpringBootLearn.demo.dto.ExpenseTrackerResponseDTO;
import com.SpringBootLearn.demo.entity.ExpenseTracker;
import org.apache.coyote.BadRequestException;
import org.springframework.data.domain.Page;


import java.util.List;

public interface ExpenseTrackerService {
    List<ExpenseTrackerResponseDTO> addExpense(List<ExpenseTrackerRequestDTO> expense);

    List<ExpenseTrackerResponseDTO> getAllExpenses();

    Page<ExpenseTrackerResponseDTO> getAllExpensesByPage(Integer page,Integer size,String sortby,String sorttype, String category, Integer minAmount, Integer maxAmount) throws BadRequestException;

    ExpenseTrackerResponseDTO getExpenseById(Integer id);

    ExpenseTrackerResponseDTO updateExpense(Integer id, ExpenseTrackerRequestDTO expense);

    String deleteExpense(Integer id);
}
