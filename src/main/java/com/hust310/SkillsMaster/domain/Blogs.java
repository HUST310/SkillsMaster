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
 * @TableName blogs
 */
@TableName(value ="blogs")
@Data
public class Blogs implements Serializable {
    /**
     * 唯一标识
     */
    @TableId(type = IdType.AUTO)
    private Integer uid;

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
    private Integer likes;

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

    /**
     * 
     */
    private String title;

    /**
     * 
     */
    private String tag;

    /**
     * 
     */
    private String img;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    public void addLikes(){
       ++likes;
    }

    public void addComment(){
       ++comment;
    }

    public String[] getTagArray(){
        if(tag == null || tag.trim().length() == 0){
            return null;
        }
        return tag.split(",");
    }
}