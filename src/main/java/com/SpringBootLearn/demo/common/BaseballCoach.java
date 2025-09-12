package com.SpringBootLearn.demo.common;

import com.SpringBootLearn.demo.interfaces.Coach;
import org.springframework.stereotype.Component;

@Component
public class BaseballCoach implements Coach {
    public BaseballCoach(){
        System.out.println("In Constructor"+getClass().getSimpleName());
    }
    @Override
    public String getDailyWorkout() {
        return "Practice batting for 30 minutes daily";
    }
}
