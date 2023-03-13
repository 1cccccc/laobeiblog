package com.xi.service.Impl;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.jwk.RSAKey;
import com.xi.entity.UserEntity;
import com.xi.exception.JwtInvalidException;
import com.xi.mapper.UserMapper;
import com.xi.security.JwtUtils;
import com.xi.security.LoginUserEntity;
import com.xi.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements UserService {
    @Resource
    AuthenticationManager authenticationManager;
    @Resource
    StringRedisTemplate stringRedisTemplate;

    private final RSAKey rsaKey=JwtUtils.loadJKSByClassPath();

    @Override
    public String login(UserEntity userEntity) {
        //将用户名和密码封装成Authentication对象
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userEntity.getUsername(), userEntity.getPassword());

        //验证
        //他需要一个Authentication对象
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);

        //验证未通过
        //如果认证不通过这个authenticate就会是null，认证不通过Security也会报异常，我只是保险做法
        if(Objects.isNull(authenticate)){
            throw  new BadCredentialsException("认证不通过，检查您的用户名和密码");
        }

        //验证通过,查询对应的用户信息放入redis表示登录
        LoginUserEntity loginUserEntity = (LoginUserEntity) authenticate.getPrincipal();
        UserEntity user = new UserEntity();
        user.setUserId(loginUserEntity.getUserEntity().getUserId()).setUsername(loginUserEntity.getUsername());

        String token = null;
        try {
            token = JwtUtils.generateTokenByRSA(user, rsaKey);
        } catch (JOSEException e) {
            throw new BadCredentialsException("token无效");
        }

        //将用户信息存入redis,有效期为2个小时
        stringRedisTemplate.opsForValue().set("user:"+loginUserEntity.getUserEntity().getUserId(),JSON.toJSONString(loginUserEntity.getUserEntity()),2, TimeUnit.HOURS);

        return token;
    }
}
