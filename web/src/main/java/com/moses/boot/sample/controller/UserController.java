package com.moses.boot.sample.controller;

import com.moses.boot.sample.model.User;
import com.moses.boot.sample.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/user/save")
    public boolean saveUser(String name){
        User u = new User();
        u.setName(name);
        return userRepository.saveUser(u);
    }


}
