package com.hust310.SkillsMaster.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hust310.SkillsMaster.domain.Blogs;
import com.hust310.SkillsMaster.domain.Follow;
import com.hust310.SkillsMaster.domain.Test;
import com.hust310.SkillsMaster.service.BlogsService;
import com.hust310.SkillsMaster.service.FollowService;
import com.hust310.SkillsMaster.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@RestController
public class BlogController {
    @Autowired
    private FollowService followService;
    @Autowired
    private BlogsService blogsService;
    @Autowired
    private TestService testService;


    @PostMapping("index")
    public List<Blogs> getBlogs(HttpSession session, Integer page) {
        session.setAttribute("uid", 123);
        Integer uid = (Integer) session.getAttribute("uid");
        List<Integer> bloggers = followService.list(new QueryWrapper<Follow>().eq("follower", uid))
                .stream().map(Follow::getBlogger).collect(Collectors.toList());
        Date currentTime = new Date();
        currentTime.setTime(currentTime.getTime() - 1000L * 7 * 24 * 60 * 60);
        List<Blogs> blogs = blogsService.list(new QueryWrapper<Blogs>().in("account", bloggers).gt("time", currentTime));
        return blogs;
    }


    @PostMapping("/getHot")
    public Page<Blogs> getHot(Integer page) {
        Page<Blogs> hot = blogsService.page(new Page<Blogs>(page, 10), new QueryWrapper<Blogs>().orderByAsc("likes"));
        return hot;
    }


    @PostMapping("/getTest")
    public Page<Test> getTest(Integer page) {
        Page<Test> testPage = testService.page(new Page<Test>(page, 10), new QueryWrapper<Test>().orderByAsc("a"));
        return testPage;
    }
}
