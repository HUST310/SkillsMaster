package com.hust310.SkillsMaster;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hust310.SkillsMaster.domain.User;
import com.hust310.SkillsMaster.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;

public class TestTime {
    @Test
    public void test() {
        Date date = new Date();
        date.setTime(date.getTime() - 1000L * 7 * 24 * 60 * 60);
        System.out.println(date);
//        System.out.println("hello");
    }


    @Test
    public void testUser() throws JsonProcessingException {
        User user = new User();
        user.setUsername("1234");
        ObjectMapper objectMapper = new ObjectMapper();
        String s = objectMapper.writeValueAsString(user);
        System.out.println(s);
//        LinkedHashMap<String, Object>
    }

    @Test
    public void classs() {
        String name = "123";
        switch (name) {
            case "123":
                System.out.println(name);
                break;
            default:
                break;
        }
    }

    @Test
    public void path() {
        System.out.println(this.getClass().getResource("/").getPath().substring(1));
    }
}
