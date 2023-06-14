package com.hust310.SkillsMaster.controller;

import com.hust310.SkillsMaster.domain.User;
import com.hust310.SkillsMaster.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/hello")
    public String getHello() {
        return "hello";
    }

    @GetMapping("/user/getAll")
    public List<User> getUser() {
        return userService.list();
    }

    @GetMapping("/user/modify")
    public void modifyUser(@RequestBody User user) {
        userService.saveOrUpdate(user);
    }
}
