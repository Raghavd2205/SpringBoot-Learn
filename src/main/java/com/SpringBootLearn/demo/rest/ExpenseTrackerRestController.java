package com.SpringBootLearn.demo.rest;

import com.SpringBootLearn.demo.common.ApiResponse;
import com.SpringBootLearn.demo.dto.ExpenseTrackerRequestDTO;
import com.SpringBootLearn.demo.dto.ExpenseTrackerResponseDTO;
import com.SpringBootLearn.demo.service.ExpenseTrackerService;
import com.SpringBootLearn.demo.utility.ResponseUtil;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/api/v1/expense")
public class ExpenseTrackerRestController {
    private final ExpenseTrackerService expenseTrackerService;

    public ExpenseTrackerRestController(ExpenseTrackerService expenseTrackerService) {
        this.expenseTrackerService = expenseTrackerService;
    }

    @PostMapping
    ResponseEntity<ApiResponse<ExpenseTrackerResponseDTO>> addExpense(@RequestBody ExpenseTrackerRequestDTO expenseBody){
        return ResponseUtil.success(this.expenseTrackerService.addExpense(expenseBody),201);
    }
    @GetMapping
    ResponseEntity <ApiResponse<List<ExpenseTrackerResponseDTO>>> findAllExpense(){
        List<ExpenseTrackerResponseDTO> exp =  this.expenseTrackerService.getAllExpenses();
        return ResponseUtil.success(exp,200);
    }
    @GetMapping("/{id}")
    ResponseEntity<ExpenseTrackerResponseDTO> findExpenseById(@PathVariable Integer id){
        return ResponseEntity.ok(this.expenseTrackerService.getExpenseById(id));
    }
    @PutMapping("/{id}")
    ResponseEntity<ExpenseTrackerResponseDTO> updateExpenseById(@PathVariable Integer id, @RequestBody ExpenseTrackerRequestDTO expenseBody){
        return ResponseEntity.status(201).body(this.expenseTrackerService.updateExpense(id,expenseBody));
    }
    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteExpenseById(@PathVariable Integer id){
        return ResponseEntity.ok(this.expenseTrackerService.deleteExpense(id));
    }
}
