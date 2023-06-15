package com.hust310.SkillsMaster.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName tags
 */
@TableName(value ="tags")
@Data
public class Tags implements Serializable {
    /**
     * 博客
     */
    private String uid;

    /**
     * 标签
     */
    private String tag;

    /**
     * 设置时间
     */
    private Date time;

    /**
     * 标签的状态
     */
    private Object state;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}