package com.github.pavelvil.spring.lifecycle.config;


import com.github.pavelvil.spring.lifecycle.bean.CoffeeShop;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.github.pavelvil.spring.lifecycle")
public class LifecycleConfiguration {

//    @Bean(initMethod = "openShop", destroyMethod = "closeShop")
//    public CoffeeShop coffeeShop() {
//        return new CoffeeShop();
//    }


}
