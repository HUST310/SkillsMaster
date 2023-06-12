package com.hust310.SkillsMaster.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public
class User {
    @TableId(value = "name")
    private String name;
    @TableField(value = "age")
    private String age;
}