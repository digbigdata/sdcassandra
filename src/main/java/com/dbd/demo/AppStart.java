package com.dbd.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.dbd.demo"})
public class AppStart {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(AppStart.class, args);
    }

}
