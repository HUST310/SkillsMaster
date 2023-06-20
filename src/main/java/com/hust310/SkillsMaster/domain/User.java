package com.hust310.SkillsMaster.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @TableName user
 */
@TableName(value = "user")
@Data
public class User implements Serializable {
    /**
     * 账号
     */
    @TableId(type = IdType.AUTO)
    private Integer account;

    /**
     * 密码
     */
    private String password;

    /**
     * 用户昵称
     */
    private String username;

    /**
     * 性别
     */
    private Object gender;

    /**
     * 用户签名
     */
    private String signature;

    /**
     * 用户头像
     */
    private String avatar;

    /**
     * 用户创建时间
     */
    private Date time;

    /**
     * 账号状态
     */
    private Object state;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}