package com.hust310.SkillsMaster.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 
 * @TableName keywords
 */
@TableName(value ="keywords")
@Data
public class Keywords implements Serializable {
    /**
     * 博客
     */
    private String uid;

    /**
     * 关键词
     */
    private String keyword;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}