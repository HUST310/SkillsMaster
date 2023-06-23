package com.hust310.SkillsMaster.controller;

import com.hust310.SkillsMaster.domain.Blogs;
import com.hust310.SkillsMaster.service.BlogsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class TagController {
    @Autowired
    private BlogsService blogsService;




    @PostMapping("/label/change")
    public String changeLabels(@RequestBody List<Map<String, Object>> blogs) {
        ArrayList<Blogs> tags = new ArrayList<>();


        for (Map blog : blogs) {
            Blogs blog1 = new Blogs();
            blog1.setUid((Integer) blog.get("uid"));
            blog1.setTag(String.join(",", (List<String>) blog.get("tag")));
            tags.add(blog1);
        }
        blogsService.saveOrUpdateBatch(tags);
        return "success";
    }

}