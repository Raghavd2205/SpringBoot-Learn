package com.SpringBootLearn.demo.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FirstRestController {
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
