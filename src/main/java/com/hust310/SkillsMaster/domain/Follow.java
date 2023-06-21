package com.hust310.SkillsMaster.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName follow
 */
@TableName(value ="follow")
@Data
public class Follow implements Serializable {
    /**
     * 博主
     */
    private Integer blogger;

    /**
     * 关注者
     */
    private Integer follower;

    /**
     * 关注时间
     */
    private Date time;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;




}