package com.SpringBootLearn.demo.service;

import com.SpringBootLearn.demo.dao.ExpenseTrackerRepository;
import com.SpringBootLearn.demo.dto.ExpenseTrackerRequestDTO;
import com.SpringBootLearn.demo.dto.ExpenseTrackerResponseDTO;
import org.springframework.stereotype.Service;
import com.SpringBootLearn.demo.entity.ExpenseTracker;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExpenseTrackerServiceImpl implements ExpenseTrackerService{
    private final ExpenseTrackerRepository expenseRepo;
    public ExpenseTrackerServiceImpl(ExpenseTrackerRepository expenseRepo) {
        this.expenseRepo = expenseRepo;
    }

    @Override
    public ExpenseTrackerResponseDTO addExpense(ExpenseTrackerRequestDTO expense) {
        System.out.println("expense"+expense);
        ExpenseTracker expense1 = new ExpenseTracker();
        expense1.setTitle(expense.getTitle());
        expense1.setDate(expense.getDate());
        expense1.setAmount(expense.getAmount());
        expense1.setCategory(expense.getCategory());
        return toExpenseDto(this.expenseRepo.save(expense1));
    }

    @Override
    public List<ExpenseTrackerResponseDTO> getAllExpenses() {
        return this.expenseRepo.findAll().stream()
                .map(this::toExpenseDto)
                .collect(Collectors.toList());
    }

    @Override
    public ExpenseTrackerResponseDTO getExpenseById(Integer id) {
        ExpenseTracker expense = this.expenseRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Expense not found"));
        return this.toExpenseDto(expense);
    }

    @Override
    public ExpenseTrackerResponseDTO updateExpense(Integer id, ExpenseTrackerRequestDTO expense) {
        ExpenseTracker expense1 = this.expenseRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Expense not found"));
        expense1.setCategory(expense.getCategory());
        expense1.setTitle(expense.getTitle());
        expense1.setDate(expense.getDate());
        expense1.setAmount(expense.getAmount());

        return this.toExpenseDto(this.expenseRepo.save(expense1));
    }

    @Override
    public String deleteExpense(Integer id) {
        ExpenseTracker expense1 = this.expenseRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Expense not found"));
        this.expenseRepo.deleteById(id);
        return "Expense with Id "+id+" deleted successfully !!";
    }
    private ExpenseTrackerResponseDTO toExpenseDto(ExpenseTracker expense){
        ExpenseTrackerResponseDTO expense1 = new ExpenseTrackerResponseDTO();
        expense1.setTitle(expense.getTitle());
        expense1.setDate(expense.getDate());
        expense1.setAmount(expense.getAmount());
        expense1.setCategory(expense.getCategory());
        expense1.setId(expense.getId());
        return expense1;
    }
}
