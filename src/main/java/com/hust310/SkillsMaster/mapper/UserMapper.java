package com.hust310.SkillsMaster.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hust310.SkillsMaster.config.MybatisRedisCache;
import com.hust310.SkillsMaster.domain.User;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 25972
 * @description 针对表【user】的数据库操作Mapper
 * @createDate 2023-06-14 08:46:28
 * @Entity com.hust310.SkillsMaster.domain.User
 */
@Mapper
@CacheNamespace(implementation = MybatisRedisCache.class, eviction = MybatisRedisCache.class)
//@CacheNamespace
public interface UserMapper extends BaseMapper<User> {

}




