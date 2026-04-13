package com.SpringBootLearn.demo.dto;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ExpenseTrackerResponseDTO {
    private Integer id;
    private String title;
    private Double amount;
    private String category;
    private LocalDate date;
}
