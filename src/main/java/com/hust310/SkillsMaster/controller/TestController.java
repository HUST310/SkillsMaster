package com.hust310.SkillsMaster.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hust310.SkillsMaster.domain.Test;
import com.hust310.SkillsMaster.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Autowired
    private TestService testService;

    @PostMapping("/getTest")
    public Page<Test> getTest(Integer page) {
        Page<Test> testPage = testService.page(new Page<Test>(page, 2));
        return testPage;
    }
}
