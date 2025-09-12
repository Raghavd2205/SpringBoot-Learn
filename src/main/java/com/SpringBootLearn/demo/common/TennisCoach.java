package com.SpringBootLearn.demo.common;

import com.SpringBootLearn.demo.interfaces.Coach;
import org.springframework.stereotype.Component;

@Component
public class TennisCoach implements Coach {
    public TennisCoach(){
        System.out.println("In Constructor"+getClass().getSimpleName());
    }
    @Override
    public String getDailyWorkout() {
        return "Practice Daily";
    }
}
