package com.hust310.SkillsMaster.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hust310.SkillsMaster.domain.Test;
import com.hust310.SkillsMaster.service.TestService;
import com.hust310.SkillsMaster.mapper.TestMapper;
import org.springframework.stereotype.Service;

/**
* @author 25972
* @description 针对表【test】的数据库操作Service实现
* @createDate 2023-06-16 17:04:53
*/
@Service
public class TestServiceImpl extends ServiceImpl<TestMapper, Test>
    implements TestService{

}




