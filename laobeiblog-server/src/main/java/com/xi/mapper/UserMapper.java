package com.xi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xi.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<UserEntity> {
}
