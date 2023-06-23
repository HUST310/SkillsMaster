package com.hust310.SkillsMaster;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hust310.SkillsMaster.domain.Blogs;
import com.hust310.SkillsMaster.service.BlogsService;
import com.hust310.SkillsMaster.service.TestService;
import com.hust310.SkillsMaster.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SkillsMasterApplicationTests {
    @Autowired
    private UserService userService;
    @Autowired
    private BlogsService blogsService;

    @Test
    void testInsert() {
    }

//    @Test
//    void test() {
//        User user = new User();
//        user.setAccount(1);
//        user.setUsername("ji1");
//        user.setPassword("123456");
//        System.out.println(userService.list());
//        userService.saveOrUpdate(user);
//        System.out.println(userService.list());
//
//    }

    @Autowired
    private TestService testService;

    @Test
    void testJson() {
        ;
        System.out.println(blogsService
                .getMap(new QueryWrapper<Blogs>()
                        .select("sum(likes) as sum")
                        .eq("owner", 1)));
    }


    @Test
    void testSplit(){
        String tag="c,java,后端";
        String[] split = tag.split(",");
        System.out.println(split.length);
        System.out.println(split[2]);
    }

}
