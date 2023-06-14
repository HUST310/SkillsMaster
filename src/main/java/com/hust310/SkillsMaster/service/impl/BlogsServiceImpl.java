package com.hust310.SkillsMaster.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hust310.SkillsMaster.domain.Blogs;
import com.hust310.SkillsMaster.mapper.BlogsMapper;
import com.hust310.SkillsMaster.service.BlogsService;
import org.springframework.stereotype.Service;

/**
* @author 25972
* @description 针对表【blogs】的数据库操作Service实现
* @createDate 2023-06-14 08:46:28
*/
@Service
public class BlogsServiceImpl extends ServiceImpl<BlogsMapper, Blogs>
    implements BlogsService{

}




