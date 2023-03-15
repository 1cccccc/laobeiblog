package com.xi.security;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xi.entity.UserEntity;
import com.xi.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserDetailServiceImpl implements UserDetailsService{
    @Lazy
    @Resource
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LambdaQueryWrapper<UserEntity> queryMapper = new LambdaQueryWrapper<>();
        queryMapper.eq(UserEntity::getUsername,username);
        UserEntity userEntity = userService.getOne(queryMapper);

        if(Objects.isNull(userEntity)){
            throw new UsernameNotFoundException("用户不存在");
        }

        return new LoginUserEntity(userEntity);
    }
}
