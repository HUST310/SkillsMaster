package com.hust310.SkillsMaster.mapper;

import com.hust310.SkillsMaster.config.MybatisRedisCache;
import com.hust310.SkillsMaster.domain.Blogcomments;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 25972
* @description 针对表【blogcomments】的数据库操作Mapper
* @createDate 2023-06-16 10:45:40
* @Entity com.hust310.SkillsMaster.domain.Blogcomments
*/
@Mapper
@CacheNamespace(implementation = MybatisRedisCache.class, eviction = MybatisRedisCache.class)
public interface BlogcommentsMapper extends BaseMapper<Blogcomments> {

}




