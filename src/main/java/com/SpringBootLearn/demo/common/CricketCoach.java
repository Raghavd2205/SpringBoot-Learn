package com.SpringBootLearn.demo.common;

import com.SpringBootLearn.demo.interfaces.Coach;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component

// Manually changing scope from singletone to prototype
//@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)

public class CricketCoach implements Coach {
    public CricketCoach(){
        System.out.println("In Constructor"+getClass().getSimpleName());
    }
    @Override
    public String getDailyWorkout() {
        return "Practice fast bowling for 15 minutes :)";
    }

    //PostConstruct annotation is  a life cycle method and it is same as ngOnInit lifecycle hook of angular (Called after bean is getting created)
    //Used for initialization logic like opening connections, preparing data, or validation.
    @PostConstruct
    public void definePostConstruct(){
        System.out.println("Post Construct annotation is same as ngOnInit lifecycle hook of angular");
    }
    //PreDestroy annotation is  a life cycle method and it is same as ngOnDestroy lifecycle hook of angular(Called when bean is getting destroyed)
    //Commonly used to close DB connections, shutdown threads, release resources.
    
//    For "prototype" scoped beans, Spring does not call the destroy method. Gasp!
//    For singleton beans, the container creates them once and also manages their full lifecycle (including destruction).
//
//    For prototype beans, the container’s responsibility ends after creation & dependency injection.
//
//    Spring does not track prototype beans after giving them to you.
//
//    That means no @PreDestroy and no destroy() callback.
    @PreDestroy
    public void defilePreDestroy(){
        System.out.println("Pre Destroy is same as ngOnDestroy of angular");
    }
}
