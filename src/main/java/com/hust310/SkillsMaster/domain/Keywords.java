package com.hust310.SkillsMaster.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

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