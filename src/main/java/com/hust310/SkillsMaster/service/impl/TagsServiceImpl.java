package com.hust310.SkillsMaster.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hust310.SkillsMaster.domain.Tags;
import com.hust310.SkillsMaster.service.TagsService;
import com.hust310.SkillsMaster.mapper.TagsMapper;
import org.springframework.stereotype.Service;

/**
* @author 25972
* @description 针对表【tags】的数据库操作Service实现
* @createDate 2023-06-16 10:45:40
*/
@Service
public class TagsServiceImpl extends ServiceImpl<TagsMapper, Tags>
    implements TagsService{

}




