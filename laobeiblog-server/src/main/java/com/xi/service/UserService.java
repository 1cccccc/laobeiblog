package com.xi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xi.entity.UserEntity;

import java.util.Map;

public interface UserService extends IService<UserEntity> {
    Map login(UserEntity userEntity);

    boolean loginOut();

    Map<String, Object> otherInfo(int id);

    String register(UserEntity userEntity);
}
