package com.moses.boot.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication(scanBasePackages = {"com.moses.boot"})
@ServletComponentScan(basePackages = "com.moses.boot.sample.fluxStudy")
public class SpringBootWebMultiApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootWebMultiApplication.class, args);
    }

}
