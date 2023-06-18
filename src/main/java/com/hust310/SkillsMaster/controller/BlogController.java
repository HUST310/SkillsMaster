package com.hust310.SkillsMaster.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hust310.SkillsMaster.domain.*;
import com.hust310.SkillsMaster.service.*;
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
    @Autowired
    private UserService userService;

    @PostMapping("/user/getUpdatedBlogs")
    public List<BlogResponse> getBlogs(HttpSession session, @RequestBody Map<String,Integer> page) {
        session.setAttribute("uid", 1);
        Integer account = (Integer) session.getAttribute("uid");
        List<Integer> bloggers = followService.list(new QueryWrapper<Follow>().eq("follower", account))
                .stream().map(Follow::getBlogger).collect(Collectors.toList());
        List<BlogResponse> blogResponses=new ArrayList<>();
        List<Blogs> followBlogs = blogsService.page(new Page<>(page.get("page"), 10),new QueryWrapper<Blogs>().in("owner", bloggers).orderByDesc("time")).getRecords();
        for (int i = 0; i < followBlogs.size(); i++) {
            BlogResponse blogResponse=new BlogResponse();
            Blogs followBlog =  followBlogs.get(i);
            Integer bloggerId=followBlog.getOwner();
            User blogger = userService.getById(bloggerId);
            blogResponse.setAccount(blogger.getAccount());
            blogResponse.setAvatar(blogger.getAvatar());
            blogResponse.setName(blogger.getUsername());
            blogResponse.setComment(followBlog.getComment());
            blogResponse.setLike(followBlog.getLikes());
            blogResponse.setTitle(followBlog.getTitle());
            blogResponse.setContent(followBlog.getContent());
            blogResponse.setTime(followBlog.getTime());
            blogResponse.setUid(followBlog.getUid());
            blogResponses.add(blogResponse);
        }
        return blogResponses;
    }

    @PostMapping("/user/getBlogs")
    public List<BlogResponse> getHot(@RequestBody Map<String,Integer> page) {
        List<BlogResponse> blogResponses=new ArrayList<>();
        Page<Blogs> hotPage = blogsService.page(new Page<Blogs>(page.get("page"), 10), new QueryWrapper<Blogs>().orderByDesc("likes"));
        List<Blogs> hotBlogs = hotPage.getRecords();
        for (int i = 0; i < hotBlogs.size(); i++) {
            BlogResponse blogResponse=new BlogResponse();
            Blogs hotBlog =  hotBlogs.get(i);
            Integer account=hotBlog.getOwner();
            User blogger = userService.getById(account);
            blogResponse.setAccount(blogger.getAccount());
            blogResponse.setAvatar(blogger.getAvatar());
            blogResponse.setName(blogger.getUsername());
            blogResponse.setComment(hotBlog.getComment());
            blogResponse.setLike(hotBlog.getLikes());
            blogResponse.setTitle(hotBlog.getTitle());
            blogResponse.setContent(hotBlog.getContent());
            blogResponse.setTime(hotBlog.getTime());
            blogResponse.setUid(hotBlog.getUid());
            blogResponses.add(blogResponse);
        }
        return blogResponses;
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
