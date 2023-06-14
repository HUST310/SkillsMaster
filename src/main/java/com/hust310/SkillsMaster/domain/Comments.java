package com.hust310.SkillsMaster.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName comments
 */
@TableName(value ="comments")
@Data
public class Comments implements Serializable {
    /**
     * 唯一标识
     */
    @TableId
    private String uid;

    /**
     * 回复的对象
     */
    private String receiver;

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
    private Integer like;

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