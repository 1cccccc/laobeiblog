package com.xi.service.Impl;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.jwk.RSAKey;
import com.xi.common.Constant;
import com.xi.common.RsaUtils;
import com.xi.common.Utils;
import com.xi.entity.UserEntity;
import com.xi.mapper.UserMapper;
import com.xi.security.JwtUtils;
import com.xi.security.LoginUserEntity;
import com.xi.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;
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
    public Map login(UserEntity userEntity) {
        String originPasswd=null;
        try {
            originPasswd=RsaUtils.privateDecrypt(userEntity.getPassword(),rsaKey);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        //将用户名和密码封装成Authentication对象
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userEntity.getUsername(), originPasswd);

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
        UserEntity loginUserEntityUserEntity = loginUserEntity.getUserEntity();
        UserEntity user = new UserEntity();
        user.setUserId(loginUserEntityUserEntity.getUserId()).setUsername(loginUserEntity.getUsername());

        String token = null;
        try {
            token = JwtUtils.generateTokenByRSA(user, rsaKey);
        } catch (JOSEException e) {
            throw new BadCredentialsException("token无效");
        }

        //将用户信息存入redis,有效期为2个小时
        stringRedisTemplate.opsForValue().set(Constant.REDIS_USER_PREFIX.getValue()+user.getUserId(),JSON.toJSONString(loginUserEntityUserEntity),2, TimeUnit.HOURS);

        HashMap<String, Object> map = new HashMap<>();
        map.put("token",token);
        loginUserEntityUserEntity.setPassword(null).setDeleted(null).setDeleted(null).setCreateTime(null).setUpdateTime(null);
        map.put("userinfo", loginUserEntityUserEntity);

        return map;
    }

    @Override
    public boolean loginOut() {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserEntity userEntity = (UserEntity) usernamePasswordAuthenticationToken.getPrincipal();
        Integer userId = userEntity.getUserId();

        stringRedisTemplate.delete(Constant.REDIS_USER_PREFIX.getValue() +userId);

        return true;
    }
}