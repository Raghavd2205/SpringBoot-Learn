package com.SpringBootLearn.demo.service;

import com.SpringBootLearn.demo.dao.ExpenseTrackerRepository;
import com.SpringBootLearn.demo.dto.ExpenseTrackerRequestDTO;
import com.SpringBootLearn.demo.dto.ExpenseTrackerResponseDTO;
import org.apache.coyote.BadRequestException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.config.web.server.ServerHttpSecurity;
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
    public List<ExpenseTrackerResponseDTO> addExpense(List<ExpenseTrackerRequestDTO> expenses) {
        List<ExpenseTracker> entities = expenses.stream().map(this::toEntity).toList();
        List<ExpenseTracker> response = this.expenseRepo.saveAll(entities);
        return response.stream().map(this::toExpenseDto).toList();
    }

    @Override
    public List<ExpenseTrackerResponseDTO> getAllExpenses() {
        return this.expenseRepo.findAll().stream()
                .map(this::toExpenseDto)
                .collect(Collectors.toList());
    }

    @Override
    public Page<ExpenseTrackerResponseDTO> getAllExpensesByPage(Integer page,Integer size,String sortby,String sorttype,String category, Integer minAmount, Integer maxAmount) throws BadRequestException {
        if(!List.of("id", "amount", "title").contains(sortby)){
            throw new BadRequestException("Invalid sort field");
        }
        Sort sort = sorttype.equalsIgnoreCase("desc")?
                    Sort.by(sortby).descending():
                    Sort.by(sortby).ascending();
        Pageable pageable = PageRequest.of(page, size,sort);
        Page<ExpenseTracker> pageData = this.expenseRepo.filterExpenses(category,minAmount,maxAmount,pageable);

        return pageData.map(this::toExpenseDto);

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
    private ExpenseTracker toEntity(ExpenseTrackerRequestDTO expense){
        ExpenseTracker expense1 = new ExpenseTracker();
        expense1.setTitle(expense.getTitle());
        expense1.setDate(expense.getDate());
        expense1.setAmount(expense.getAmount());
        expense1.setCategory(expense.getCategory());
        return expense1;
    }
}
