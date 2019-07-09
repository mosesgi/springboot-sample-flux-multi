package com.moses.boot.sample.controller;

import com.moses.boot.sample.model.User;
import com.moses.boot.sample.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

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

    @PostMapping("/user/saveuser")
    public User save(@Valid @RequestBody User user){
        return user;
    }

    @PostMapping("/user/saveuserAssert")
    public User saveUsingAssertApi(@RequestBody User user){
        //API调用的方式
        Assert.hasText(user.getName(), "名称不能为空");
        //JVM 断言
        assert user.getId() <=10000;
        return user;
    }
}
