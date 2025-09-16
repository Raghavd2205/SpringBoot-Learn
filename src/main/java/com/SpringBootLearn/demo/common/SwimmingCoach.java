package com.SpringBootLearn.demo.common;

import com.SpringBootLearn.demo.interfaces.Coach;

public class SwimmingCoach implements Coach {
    public SwimmingCoach(){
        System.out.print("Swimming Coach !!");
    }
    @Override
    public String getDailyWorkout() {
        return "Swim for 5k meters daily";
    }
}
