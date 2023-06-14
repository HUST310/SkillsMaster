package com.hust310.SkillsMaster.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hust310.SkillsMaster.domain.Comments;
import com.hust310.SkillsMaster.mapper.CommentsMapper;
import com.hust310.SkillsMaster.service.CommentsService;
import org.springframework.stereotype.Service;

/**
* @author 25972
* @description 针对表【comments】的数据库操作Service实现
* @createDate 2023-06-14 08:46:28
*/
@Service
public class CommentsServiceImpl extends ServiceImpl<CommentsMapper, Comments>
    implements CommentsService{

}




