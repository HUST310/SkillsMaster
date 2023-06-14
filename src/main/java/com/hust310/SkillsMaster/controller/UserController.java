package com.hust310.SkillsMaster.controller;

import com.hust310.SkillsMaster.domain.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @GetMapping("/hello")
    public String getHello() {
        return "hello";
    }

    @GetMapping("/user")
    public User getUser() {
        User user = new User();
        user.setUsername("姜明");
        return user;
    }
}
