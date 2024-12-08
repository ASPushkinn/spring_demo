package com.github.pavelvil.spring.lifecycle;

import com.github.pavelvil.spring.lifecycle.config.LifecycleConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.github.pavelvil.spring.lifecycle.bean.CoffeeShop;


public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(LifecycleConfiguration.class);

        CoffeeShop shop = context.getBean(CoffeeShop.class);
        //shop.makeCoffee();

        shop.makeCoffee("Latter", "sugar");
        shop.makeCoffee("Latter", "milk");

        //shop.makeCoffee("Latter");
        //shop.makeCoffee("Latter");

        context.close();

    }

}
