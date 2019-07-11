package com.moses.boot.sample.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    @Value("${employee.name}")
    private String employeeName;

    @Value("${employee.info}")
    private String employeeInfo;


    @GetMapping("/employee/propvalue")
    @ResponseBody
    public String testPropertyValues(){
        return employeeName + "\n" + employeeInfo;
    }

    @RequestMapping("/testThymeleaf")
    public String testThymeleaf(ModelMap map){
        map.put("name", "jmc1");
        return "testThymeleaf";
    }
}
