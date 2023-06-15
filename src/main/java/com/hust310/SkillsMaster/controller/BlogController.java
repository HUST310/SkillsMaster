package com.hust310.SkillsMaster.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hust310.SkillsMaster.domain.Blogs;
import com.hust310.SkillsMaster.domain.Follow;
import com.hust310.SkillsMaster.service.BlogsService;
import com.hust310.SkillsMaster.service.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

//@Controller
@RestController
public class BlogController {
//    @Autowired
//    private FollowService followService;
//    @Autowired
//    private BlogsService blogsService;
//
//    @PostMapping("index")
//    public String getBlogs(Model model, HttpSession session, int page) {
//        session.setAttribute("uid", 123);
//        int uid = (int) session.getAttribute("uid");
////        List<Blogs>blogsOfBlogers
//        List<Follow> follower = followService.list(new QueryWrapper<Follow>().eq("follower", uid));
//
//    }
}
