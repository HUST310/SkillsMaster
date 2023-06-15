package com.hust310.SkillsMaster.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName blogs
 */
@TableName(value ="blogs")
@Data
public class Blogs implements Serializable {
    /**
     * 唯一标识
     */
    @TableId
    private String uid;

    /**
     * 博主
     */
    private Integer owner;

    /**
     * 发表时间
     */
    private Date time;

    /**
     * 点赞数量
     */
    private Integer like;

    /**
     * 评论数量
     */
    private Integer comment;

    /**
     * 博客的状态
     */
    private Object state;

    /**
     * 博客内容
     */
    private String content;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}