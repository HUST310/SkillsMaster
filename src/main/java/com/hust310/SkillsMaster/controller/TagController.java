package com.hust310.SkillsMaster.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hust310.SkillsMaster.domain.Blogs;
import com.hust310.SkillsMaster.domain.Tags;
import com.hust310.SkillsMaster.service.TagsService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class TagController {
    @Autowired
    private TagsService tagsService;

    @PostMapping("/label/change")
    public String changeLabels(@RequestBody List<Map<String, Object>> blogs) {
        ArrayList<Tags> tags = new ArrayList<>();
        for (Map blog : blogs) {
            tagsService.remove(new QueryWrapper<Tags>().eq("uid", blog.get("uid")));
            for (String s : (ArrayList<String>) blog.get("content")) {
                Tags tag = new Tags();
                tag.setUid((Integer) blog.get("uid"));
                tag.setTag(s);
                tags.add(tag);
            }
        }
        tagsService.saveBatch(tags);
        return "success";
    }
}

@Data
class Labels {
    private Integer uid;
    private List<String> content;
}
