package com.SpringBootLearn.demo.rest;

import com.SpringBootLearn.demo.dto.ExpenseTrackerRequestDTO;
import com.SpringBootLearn.demo.dto.ExpenseTrackerResponseDTO;
import com.SpringBootLearn.demo.service.ExpenseTrackerService;
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
    ResponseEntity<ExpenseTrackerResponseDTO> addExpense(@RequestBody ExpenseTrackerRequestDTO expenseBody){
        return ResponseEntity.status(201).body(this.expenseTrackerService.addExpense(expenseBody));
    }
    @GetMapping
    ResponseEntity<List<ExpenseTrackerResponseDTO>> findAllExpense(){
        return ResponseEntity.ok(this.expenseTrackerService.getAllExpenses());
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
