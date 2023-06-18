package com.hust310.SkillsMaster.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hust310.SkillsMaster.domain.Blogcomments;
import com.hust310.SkillsMaster.domain.Ccomments;
import com.hust310.SkillsMaster.service.BlogcommentsService;
import com.hust310.SkillsMaster.service.CcommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommentController {
    @Autowired
    private BlogcommentsService blogcommentsService;
    @Autowired
    private CcommentsService ccommentsService;

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
}
//@Mapper
//@CacheNamespace(implementation = MybatisRedisCache.class, eviction = MybatisRedisCache.class)
