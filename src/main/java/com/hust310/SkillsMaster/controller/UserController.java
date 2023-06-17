package com.hust310.SkillsMaster.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hust310.SkillsMaster.domain.User;
import com.hust310.SkillsMaster.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.LinkedHashMap;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public String login(HttpSession session, @RequestBody User user) {
        User sqluser = userService.getOne(new QueryWrapper<User>().eq("account", user.getAccount()));

        if (sqluser == null) {
            return "account error";
        } else if (!user.getPassword().equals(sqluser.getPassword())) {
            return "password error";
        } else {
            session.setAttribute("uid", user.getAccount());
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

    @GetMapping("/getUserInfo")
    public User getUserInfo(HttpSession session) {
        Integer uid = (Integer) session.getAttribute("uid");
        return userService.getOne(new QueryWrapper<User>().eq("account", uid));
    }

    @PostMapping("/modifyUserInfo")
    public String modifyUserInfo(MultipartFile file) {
        return "success";
    }
}
