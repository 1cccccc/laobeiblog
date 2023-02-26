package com.xi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xi.entity.CommentEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommentMapper extends BaseMapper<CommentEntity> {
}
