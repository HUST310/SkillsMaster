package com.hust310.SkillsMaster.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hust310.SkillsMaster.domain.User;
import com.hust310.SkillsMaster.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

//@Controller
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public String login(HttpSession session, Integer id, String password) {
        User user = userService.getById(id);
        if (user == null) {
            return "account error";
        } else if (user.getPassword() != password) {
            return "password error";
        } else {
            session.setAttribute("uid", id);
            return "success";
        }
    }

    @PostMapping("/register")
    public Integer register(@RequestBody User user) {
        if (userService.getOne(
                new QueryWrapper<User>().
                        eq("username", user.getUsername())) == null) {
            userService.save(user);
            return userService.
                    getOne(new QueryWrapper<User>().
                            eq("username", user.getUsername())).getAccount();
        } else {
            return null;
        }
    }
}
