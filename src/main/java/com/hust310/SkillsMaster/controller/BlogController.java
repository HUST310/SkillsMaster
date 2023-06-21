package com.hust310.SkillsMaster.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hust310.SkillsMaster.domain.BlogResponse;
import com.hust310.SkillsMaster.domain.Blogs;
import com.hust310.SkillsMaster.domain.Follow;
import com.hust310.SkillsMaster.domain.User;
import com.hust310.SkillsMaster.service.BlogsService;
import com.hust310.SkillsMaster.service.FollowService;
import com.hust310.SkillsMaster.service.TagsService;
import com.hust310.SkillsMaster.service.UserService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.*;
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
    public List<BlogResponse> getBlogs(HttpSession session, @RequestBody Map<String, Integer> request) {
        session.setAttribute("uid", 1);
        Integer account = (Integer) session.getAttribute("uid");
        List<Integer> bloggers = followService.list(new QueryWrapper<Follow>().eq("follower", account))
                .stream().map(Follow::getBlogger).collect(Collectors.toList());
        List<BlogResponse> blogResponses = new ArrayList<>();
        List<Blogs> followBlogs = blogsService.page(new Page<>(request.get("page"), 10), new QueryWrapper<Blogs>().in("owner", bloggers).orderByDesc("time")).getRecords();
        for (int i = 0; i < followBlogs.size(); i++) {
            BlogResponse blogResponse = new BlogResponse();
            Blogs followBlog = followBlogs.get(i);
            Integer bloggerId = followBlog.getOwner();
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
    public List<BlogResponse> getHot(@RequestBody Map<String, Integer> request) {
        List<BlogResponse> blogResponses = new ArrayList<>();
        Page<Blogs> hotPage = blogsService.page(new Page<Blogs>(request.get("page"), 10), new QueryWrapper<Blogs>().orderByDesc("likes"));
        List<Blogs> hotBlogs = hotPage.getRecords();
        for (int i = 0; i < hotBlogs.size(); i++) {
            BlogResponse blogResponse = new BlogResponse();
            Blogs hotBlog = hotBlogs.get(i);
            Integer account = hotBlog.getOwner();
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

    @PostMapping("/user/getBlogsOfBlogger")
    public List<BlogResponse> getBlogsOfBlogger(@RequestBody Map<String, Integer> request) {
        List<BlogResponse> blogResponses = new ArrayList<>();
        QueryWrapper<Blogs> blogsQueryWrapper = new QueryWrapper<Blogs>().eq("owner", request.get("account")).orderByDesc("time");
        List<Blogs> blogs = blogsService.page(new Page<Blogs>(request.get("page"), 10), blogsQueryWrapper).getRecords();
        for (int i = 0; i < blogs.size(); i++) {
            BlogResponse blogResponse = new BlogResponse();
            Blogs blog = blogs.get(i);
            blogResponse.setComment(blog.getComment());
            blogResponse.setLike(blog.getLikes());
            blogResponse.setTitle(blog.getTitle());
            blogResponse.setContent(blog.getContent());
            blogResponse.setTime(blog.getTime());
            blogResponse.setUid(blog.getUid());
            blogResponses.add(blogResponse);
        }
        return blogResponses;
    }


    @GetMapping("/blogManage/get")
    public List<Blogs> getAllBlogs(HttpSession session) {
        session.setAttribute("uid", 1);
        List<Blogs> blogs = blogsService.list(new QueryWrapper<Blogs>().
                eq("owner", session.getAttribute("uid")).eq("state", "N"));
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

    @PostMapping("/write")
    public String addBlog(@RequestBody Map<String, Object> param, HttpSession session) throws IOException {
        Blogs blog = new Blogs();

        if (param.containsKey("uid")) {
            blog.setUid((Integer) param.get("uid"));
        }
        blog.setTitle((String) param.get("title"));

        String fileName = UUID.randomUUID() + ".html";
        File file = new File(this.getClass().getResource("/")
                .getPath().substring(1) + "static/blogs", fileName);
        FileUtils.write(file, param.get("content").toString());
        blog.setContent("blogs/" + fileName);
        blog.setTag(String.join(",", (List<String>) param.get("value1")));
        blog.setOwner((Integer) session.getAttribute("uid"));
        blogsService.saveOrUpdate(blog);
        return "success";
    }

    @GetMapping("/Write/get")
    public Map<String, Object> getBlog(@RequestParam("uid") Integer uid) {
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        Blogs byId = blogsService.getById(uid);
        map.put("title", byId.getTitle());
        map.put("content", byId.getContent());
        map.put("value1", byId.getTag().split(","));
        return map;
    }
}
