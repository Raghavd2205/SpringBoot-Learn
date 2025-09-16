package com.SpringBootLearn.demo.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.SpringBootLearn.demo.interfaces.Coach;
@RestController
public class FirstRestController {
    private Coach myCoach;
    private Coach myCoach2;
    //Field Injection nopt recommendede by spring.io team because it makes the code harder to unit test
//    @Autowire
//    @Qualifier("baseballCoach")
//    private Coach myCoach;

    //Auto wiring using constructor , need to use when we have all required dependency (recommendede by spring.io team)
    @Autowired
    public FirstRestController(@Qualifier("cricketCoach") Coach theCoach,@Qualifier("aquatic") Coach theCoach2){
        System.out.println("In Constructor"+getClass().getSimpleName());
        myCoach = theCoach;
        myCoach2 = theCoach2;
    }

    //Auto wiring using setter method, need to use when we have some optional dependency
//    @Autowired
//    @Qualifier("baseballCoach")
//    public void setCoach(Coach theCoach){
//        myCoach = theCoach;
//    }
    @GetMapping("/dailyworkout")
    public String getDailyWorkout() {
        return myCoach2.getDailyWorkout();
    }
    @GetMapping("/checkscope")
    public boolean check() {
        // It will return true since by default scope is singletone in srping boot each instance point to the the same bean untill unless you change scope to prototype by your own
        return (myCoach == myCoach2);
    }
    @GetMapping("/")
    public String sayHello() {
        return "Hello World !!";
    }

    @GetMapping("/getWork")
    public String getWork() {
        return "Work Done!!";
    }
    @Value("${coach.name}")
    private String coachName;
    @Value("${team.name}")
    private String teamName;
    @GetMapping("/getdetails")
    public String getName() {
        return "Coach Name is:"+coachName+" \nTeam Name is:"+teamName;
    }
}
