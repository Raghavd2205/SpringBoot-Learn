package com.SpringBootLearn.demo.common;

import com.SpringBootLearn.demo.interfaces.Coach;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy //It will load only when called explicitly. by default all java beans are getting called during loading timne but if we use Lazy annotation it will not. (Work same as Lazy Loading of angular)
public class TrackCoach  implements Coach {
    public TrackCoach(){
        System.out.println("In Constructor"+getClass().getSimpleName());
    }
    @Override
    public String getDailyWorkout() {
        return "Run 5KM Daily";
    }
}
