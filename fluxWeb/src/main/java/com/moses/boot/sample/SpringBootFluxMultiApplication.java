package com.moses.boot.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.moses.boot"})
public class SpringBootFluxMultiApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootFluxMultiApplication.class, args);
    }

}
