package com.hust310.SkillsMaster.mapper;

import com.hust310.SkillsMaster.domain.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 25972
* @description 针对表【user】的数据库操作Mapper
* @createDate 2023-06-14 08:46:28
* @Entity com.hust310.SkillsMaster.domain.User
*/
@Mapper
public interface UserMapper extends BaseMapper<User> {

}




