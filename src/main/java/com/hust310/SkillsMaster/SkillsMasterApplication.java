package com.hust310.SkillsMaster;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;

@EnableCaching
@SpringBootApplication
public class SkillsMasterApplication {


    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(SkillsMasterApplication.class, args);
    }

}


