package com.SpringBootLearn.demo.dao;

import com.SpringBootLearn.demo.entity.ExpenseTracker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseTrackerRepository  extends JpaRepository<ExpenseTracker, Integer>{
}
