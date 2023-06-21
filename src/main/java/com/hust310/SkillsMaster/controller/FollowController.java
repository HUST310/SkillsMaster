package com.hust310.SkillsMaster.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hust310.SkillsMaster.domain.Follow;
import com.hust310.SkillsMaster.domain.User;
import com.hust310.SkillsMaster.service.FollowService;
import com.hust310.SkillsMaster.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class FollowController {
    @Autowired
    private UserService userService;
    @Autowired
    private FollowService followService;

    @GetMapping("/Follow/get")
    public List<User> getFollow(HttpSession session) {
//        session.setAttribute("uid", 1);
        List<Integer> follows = followService.
                list(new QueryWrapper<Follow>()
                        .eq("follower", session.getAttribute("uid"))).stream()
                .map(Follow::getBlogger).collect(Collectors.toList());
        if (follows.size() != 0) {
            List<User> users = userService.list(new QueryWrapper<User>().in("account", follows));
            for (User user : users) {
                user.setPassword("");
            }
            return users;
        }
        return null;
    }

    @PostMapping("/user/follow")
    public Boolean follow(HttpSession session,@RequestBody Map<String,Integer> request) {
        //session.setAttribute("uid", 1);
        Follow follow=new Follow();
        follow.setFollower((Integer) session.getAttribute("uid"));
        follow.setBlogger(request.get("account"));
        followService.save(follow);
        return true;
    }




    @PostMapping("/follow/deleteFollow")
    public String unFollow(@RequestBody List<Integer> accounts, HttpSession session) {
//        session.setAttribute("uid", 1);
        followService.remove(new QueryWrapper<Follow>().eq("follower", session.getAttribute("uid")).in("blogger", accounts));
        return "success";
    }
}
