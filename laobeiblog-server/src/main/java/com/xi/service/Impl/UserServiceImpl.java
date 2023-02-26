package com.xi.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xi.entity.UserEntity;
import com.xi.mapper.UserMapper;
import com.xi.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements UserService {
}
