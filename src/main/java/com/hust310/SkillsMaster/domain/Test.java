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
 * @TableName test
 */
@TableName(value ="test")
@Data
public class Test implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 
     */
    private Integer a;

    /**
     * 
     */
    private Date b;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}