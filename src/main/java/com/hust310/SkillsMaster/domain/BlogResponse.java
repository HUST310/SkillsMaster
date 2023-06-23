package com.hust310.SkillsMaster.domain;

import lombok.Data;

import java.util.Date;

@Data
public class BlogResponse {


    private Date time;

    private Integer like;

    private Integer uid;

    private Integer comment;

    private String content;

    private String title;

    private Integer account;

    private String avatar;

    private String name;

    private String[] tag;

}
