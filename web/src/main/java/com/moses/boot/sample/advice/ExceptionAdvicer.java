package com.moses.boot.sample.advice;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice(basePackages = "com.moses.boot.sample.controller")
public class ExceptionAdvicer {

    @ExceptionHandler(value = NullPointerException.class)
    public Object handleNPE(Throwable throwable){
        Map<String, Object> data = new HashMap<>();
        data.put("message", throwable.getMessage());
        return data;
    }
}
