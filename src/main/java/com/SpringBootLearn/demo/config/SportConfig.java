package com.SpringBootLearn.demo.config;

import com.SpringBootLearn.demo.common.SwimmingCoach;
import com.SpringBootLearn.demo.interfaces.Coach;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SportConfig {
    // bean will be mostly used for accessing property of 3rd party class like working with aws s3 and all..
    @Bean("aquatic")
    public Coach SwimmingCoach(){
        return new SwimmingCoach();
    }
}
