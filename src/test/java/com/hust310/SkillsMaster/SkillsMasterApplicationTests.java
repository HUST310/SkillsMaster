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
        User user = new User();
        user.setAccount(2);
        user.setUsername("jiangming");
        user.setPassword("123456");
        userService.save(user);
    }

}
