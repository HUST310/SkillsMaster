package com.hust310.SkillsMaster.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hust310.SkillsMaster.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
