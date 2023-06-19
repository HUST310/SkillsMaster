package com.hust310.SkillsMaster.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hust310.SkillsMaster.domain.Blogcomments;
import com.hust310.SkillsMaster.domain.Blogs;
import com.hust310.SkillsMaster.domain.Ccomments;
import com.hust310.SkillsMaster.service.BlogcommentsService;
import com.hust310.SkillsMaster.service.BlogsService;
import com.hust310.SkillsMaster.service.CcommentsService;
import com.hust310.SkillsMaster.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
public class CommentController {
    @Autowired
    private BlogcommentsService blogcommentsService;
    @Autowired
    private CcommentsService ccommentsService;
    @Autowired
    private BlogsService blogsService;
    @Autowired
    private UserService userService;

    @PostMapping("/getBlogComments")
    public Page<Blogcomments> getBlogComments(Integer uid, Integer page) {
        Page<Blogcomments> blogcommentsPage = blogcommentsService.page(new Page<>(page, 5), new QueryWrapper<Blogcomments>().eq("uid", uid));
        return blogcommentsPage;
    }

    @PostMapping("/getComments")
    public Page<Ccomments> getComments(Integer uid, Integer page) {
        Page<Ccomments> ccommentsPage = ccommentsService.page(new Page<>(page, 5), new QueryWrapper<Ccomments>().eq("uid", uid));
        return ccommentsPage;
    }

    @GetMapping("/Comments/get")
    public List<Map<String, Object>> getComments(HttpSession session) {
        session.setAttribute("uid", 1);
        Integer uid = (Integer) session.getAttribute("uid");
        String username = userService.getById(uid).getUsername();
        ArrayList<Map<String, Object>> comments = new ArrayList<>();
        List<Blogs> blogs = blogsService.list(new QueryWrapper<Blogs>().eq("owner", uid).eq("state", "N"));
        for (Blogs blog : blogs) {
            List<Blogcomments> blogcomments = blogcommentsService.list(new QueryWrapper<Blogcomments>()
                    .eq("receiver", blog.getUid()).ne("state", "D"));
            for (Blogcomments blogcomment : blogcomments) {
                LinkedHashMap<String, Object> map = new LinkedHashMap<>();
                map.put("uid", blogcomment.getUid());
                map.put("text", blogcomment.getContent());
                map.put("data", blogcomment.getTime());
                map.put("State", blogcomment.getState());
                map.put("id", username);
                map.put("blog", blog.getTitle());
                map.put("level", 1);
                comments.add(map);
            }

        }
        int len = comments.size();
        for (int i = 0; i < len; i++) {
            List<Ccomments> ccomments = ccommentsService.
                    list(new QueryWrapper<Ccomments>()
                            .eq("receiver", comments.get(i).get("uid")).ne("state", "D"));
            for (Ccomments ccomment : ccomments) {
                LinkedHashMap<String, Object> map = new LinkedHashMap<>();
                map.put("uid", ccomment.getUid());
                map.put("text", ccomment.getContent());
                map.put("data", ccomment.getTime());
                map.put("State", ccomment.getState());
                map.put("id", username);
                map.put("blog", comments.get(i).get("text"));
                map.put("level", 2);
                comments.add(map);
            }
        }
        return comments;
    }

    @PostMapping("/Comments/add")
    public String addN(@RequestBody Map<String, Object> map) {
        Integer uid = (Integer) map.get("uid");
        if (map.get("level").equals(1)) {
            Blogcomments comment = blogcommentsService.getById(uid);
            comment.setState("N");
            comment.setTime(null);
            blogcommentsService.saveOrUpdate(comment);
        } else {
            Ccomments ccomments = ccommentsService.getById(uid);
            ccomments.setState("N");
            ccomments.setTime(null);
            ccommentsService.saveOrUpdate(ccomments);
        }
        return "success";
    }

    @PostMapping("/Comments/add2")
    public String addD(@RequestBody Map<String, Object> map) {
        Integer uid = (Integer) map.get("uid");
        if (map.get("level").equals(1)) {
            Blogcomments comment = blogcommentsService.getById(uid);
            comment.setState("D");
            comment.setTime(null);
            blogcommentsService.saveOrUpdate(comment);
        } else {
            Ccomments ccomments = ccommentsService.getById(uid);
            ccomments.setState("D");
            ccomments.setTime(null);
            ccommentsService.saveOrUpdate(ccomments);
        }
        return "success";
    }
}
//@Mapper
//@CacheNamespace(implementation = MybatisRedisCache.class, eviction = MybatisRedisCache.class)
