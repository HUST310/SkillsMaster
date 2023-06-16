package com.hust310.SkillsMaster;

import org.junit.jupiter.api.Test;

import java.util.Date;

public class TestTime {
    @Test
    public void test() {
        Date date = new Date();
        date.setTime(date.getTime() - 1000L * 7 * 24 * 60 * 60);
        System.out.println(date);
//        System.out.println("hello");
    }
}
