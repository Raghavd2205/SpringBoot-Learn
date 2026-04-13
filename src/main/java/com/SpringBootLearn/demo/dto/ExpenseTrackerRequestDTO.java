package com.SpringBootLearn.demo.dto;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ExpenseTrackerRequestDTO {
    private String title;
    private Double amount;
    private String category;
    private LocalDate date;
}
