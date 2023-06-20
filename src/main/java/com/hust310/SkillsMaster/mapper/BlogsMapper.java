package com.hust310.SkillsMaster.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hust310.SkillsMaster.config.MybatisRedisCache;
import com.hust310.SkillsMaster.domain.Blogs;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 25972
 * @description 针对表【blogs】的数据库操作Mapper
 * @createDate 2023-06-17 15:55:57
 * @Entity com.hust310.SkillsMaster.domain.Blogs
 */
@Mapper
@CacheNamespace(implementation = MybatisRedisCache.class, eviction = MybatisRedisCache.class)
public interface BlogsMapper extends BaseMapper<Blogs> {

}




