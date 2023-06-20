package com.hust310.SkillsMaster.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName blogcomments
 */
@TableName(value ="blogcomments")
@Data
public class Blogcomments implements Serializable {
    /**
     * 唯一标识
     */
    @TableId(type = IdType.AUTO)
    private Integer uid;

    /**
     * 回复的对象
     */
    private Integer receiver;

    /**
     * 评论者
     */
    private Integer commentor;

    /**
     * 被回复者
     */
    private Integer commentee;

    /**
     * 评论时间
     */
    private Date time;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 点赞数量
     */
    private Integer likes;

    /**
     * 评论数量
     */
    private Integer comment;

    /**
     * 评论的状态
     */
    private Object state;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}