package com.github.pavelvil.spring.lifecycle.bean;

import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class CoffeeShop {

    @PostConstruct
    public void openShop() {
        System.out.println("Shop is opened");
    }


    public void makeCoffee() {
        System.out.println("Making coffee");
    }

    @PreDestroy
    public void closeShop() {
        System.out.println("Shop is closing");
    }

}
