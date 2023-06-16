package com.hust310.SkillsMaster;

import com.hust310.SkillsMaster.domain.User;
import com.hust310.SkillsMaster.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SkillsMasterApplicationTests {
    @Autowired
    private UserService userService;

    @Test
    void testInsert() {
    }

    @Test
    void test() {
        User user = new User();
        user.setAccount(1);
        user.setUsername("ji1");
        user.setPassword("123456");
        System.out.println(userService.list());
        userService.saveOrUpdate(user);
        System.out.println(userService.list());

    }

}
