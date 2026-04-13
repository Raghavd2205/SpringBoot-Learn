package com.SpringBootLearn.demo.entity;
import jakarta.persistence.*;
import java.time.LocalDate;
import lombok.*;
@Entity
@Table(name = "expenses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExpenseTracker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;
    private Double amount;
    private String category;
    private LocalDate date;
}
