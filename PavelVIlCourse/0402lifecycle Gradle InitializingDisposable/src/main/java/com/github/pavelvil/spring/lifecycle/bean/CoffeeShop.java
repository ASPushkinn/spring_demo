package com.github.pavelvil.spring.lifecycle.bean;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
//import javax.annotation.PostConstruct;
//import javax.annotation.PreDestroy;

@Component
public class CoffeeShop implements InitializingBean, DisposableBean {

//    @PostConstruct
//    public void openShop() {
//        System.out.println("Shop is opened");
//    }


    public void makeCoffee() {
        System.out.println("Making coffee");
    }

//    @PreDestroy
//    public void closeShop() {
//        System.out.println("Shop is closing");
//    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Shop is opened");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("Shop is closing");
    }


}
