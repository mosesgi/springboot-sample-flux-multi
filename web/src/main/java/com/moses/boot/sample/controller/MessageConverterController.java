package com.moses.boot.sample.controller;

import com.moses.boot.sample.model.User;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class MessageConverterController {

    @GetMapping("/user/{id}")
    public User user(@PathVariable Integer id, @RequestParam(required = false) String name) {
        User p = new User();
        p.setId(id);
        p.setName(name);
        return p;
    }

    /**
     * Headers
     * 		Accept: application/properties+user
     * 		Content-Type: application/json
     * Body
     * 		raw (json)  {"id":5, "name":"moses"}
     * @param user
     * @return
     */
    @PostMapping(value="/user/json_to_properties", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = "application/properties+user")
    public User personJsonToProps(@RequestBody User user) {
        return user;
    }

    /**
     * Headers
     * 		Accept: application/json
     * 		Content-Type: application/properties+user
     * Body
     * 		raw (text) 
     * 		user.name=moses1
     *		user.id=5
     * @param user
     * @return
     */
    @PostMapping(value="/user/properties_to_json", consumes = "application/properties+user",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public User personProsToJson(@RequestBody User user) {
        return user;
    }
}
