package com.hust310.SkillsMaster.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hust310.SkillsMaster.domain.Blogs;
import com.hust310.SkillsMaster.domain.Follow;
import com.hust310.SkillsMaster.domain.Tags;
import com.hust310.SkillsMaster.domain.Test;
import com.hust310.SkillsMaster.service.BlogsService;
import com.hust310.SkillsMaster.service.FollowService;
import com.hust310.SkillsMaster.service.TagsService;
import com.hust310.SkillsMaster.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@RestController
public class BlogController {
    @Autowired
    private FollowService followService;
    @Autowired
    private BlogsService blogsService;
    @Autowired
    private TagsService tagsService;

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


    @GetMapping("/blogManage/get")
    public List<Blogs> getAllBlogs(HttpSession session) {
        session.setAttribute("uid", 1);
        List<Blogs> blogs = blogsService.list(new QueryWrapper<Blogs>().
                eq("owner", session.getAttribute("uid")).eq("state", "N"));
        for (int i = 0; i < blogs.size(); i++) {
            List<Tags> tags = tagsService.list(new QueryWrapper<Tags>().eq("uid", blogs.get(i).getUid()));
            if (tags.size() == 0) {
                blogs.get(i).setContent("");
            } else {
                String s = "";
                for (Tags tag : tags) {
                    s += "," + tag.getTag();
                }
                blogs.get(i).setContent(s.substring(1));
            }

        }
        return blogs;
    }

    @PostMapping("/Blog/deleteBlogs")
    public String deleteBlogs(@RequestBody Map<String, List<Integer>> deleteSelection) {
        ArrayList<Blogs> blogs = new ArrayList<>();
        for (Integer id : deleteSelection.get("deleteSelection")) {
            Blogs blog = new Blogs();
            blog.setUid(id);
            blog.setState("D");
            blogs.add(blog);
        }
        blogsService.saveOrUpdateBatch(blogs);
        return "success";
    }
}
