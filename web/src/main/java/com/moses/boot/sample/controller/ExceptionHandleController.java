package com.moses.boot.sample.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
public class ExceptionHandleController {

    // @PostMapping    // Post 请求   @RequestMapping(method = RequestMethod.POST)  Create(C)
    // @GetMapping     // GET 请求    @RequestMapping(method = RequestMethod.GET) Read(R)
    // @PutMapping    // Put 请求的   @RequestMapping(method = RequestMethod.PUT) Update(U)
    // @DeleteMapping // Post 请求    @RequestMapping(method = RequestMethod.DELETE) Delete(D)
    @RequestMapping("/index")
    public String index() {
        return "Hello world";
    }

    @RequestMapping("/npe")
    public String npe() {
        throw new NullPointerException("故意抛异常!");
    }

    @RequestMapping("/data")
    public Map<String,Object> data(){
        Map<String,Object> data = new HashMap<>();
        data.put("username","moses");
        data.put("password","123456");
        return data;
    }

    //ErrorPageRegistrar
    @GetMapping("/404.html")
    public Map<String,Object> handlePageNotFound(HttpStatus status, HttpServletRequest request, Throwable throwable){
        Map<String,Object> errors = new HashMap<>();
        errors.put("statusCode", request.getAttribute("javax.servlet.error.status_code"));
        errors.put("requestUri", request.getAttribute("javax.servlet.error.request_uri"));
        return errors;
    }
}
