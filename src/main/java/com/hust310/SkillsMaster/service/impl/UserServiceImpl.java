package com.hust310.SkillsMaster.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hust310.SkillsMaster.domain.User;
import com.hust310.SkillsMaster.service.UserService;
import com.hust310.SkillsMaster.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author 25972
* @description 针对表【user】的数据库操作Service实现
* @createDate 2023-06-15 16:18:33
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




