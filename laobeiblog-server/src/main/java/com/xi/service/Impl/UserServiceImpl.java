package com.xi.service.Impl;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.jwk.RSAKey;
import com.xi.common.Constant;
import com.xi.common.RsaUtils;
import com.xi.common.Utils;
import com.xi.entity.ArticleEntity;
import com.xi.entity.CategoryEntity;
import com.xi.entity.TagEntity;
import com.xi.entity.UserEntity;
import com.xi.mapper.UserMapper;
import com.xi.security.JwtUtils;
import com.xi.security.LoginUserEntity;
import com.xi.service.ArticleService;
import com.xi.service.CategoryService;
import com.xi.service.TagService;
import com.xi.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

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

    @Resource
    PasswordEncoder passwordEncoder;

    @Resource
    ArticleService articleService;
    @Resource
    TagService tagService;

    @Resource
    CategoryService categoryService;

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

    @Override
    public Map<String, Object> otherInfo(int id) {
        long articleCount = articleService.count(new QueryWrapper<ArticleEntity>().eq("user_id",id));
        long tagCount = tagService.count(new LambdaQueryWrapper<TagEntity>().eq(TagEntity::getUserId, id));
        long categoryCount = categoryService.count(new LambdaQueryWrapper<CategoryEntity>().eq(CategoryEntity::getUserId, id));

        HashMap<String, Object> map = new HashMap<>();
        map.put("articleCount",articleCount);
        map.put("tagCount",tagCount);
        map.put("categoryCount",categoryCount);

        return map;
    }

    @Override
    public String register(UserEntity userEntity) {
        //判断验证码是否有效

        String username=userEntity.getUsername();
        //判断用户是否已经存在
        UserEntity entity = this.getOne(new LambdaQueryWrapper<UserEntity>().eq(UserEntity::getUsername, username));
        if(!Objects.isNull(entity)){
            return "用户名已存在";
        }

        //对密码进行rsa解密
        try {
            //rsa解密后进行md5加密
            String password=passwordEncoder.encode(RsaUtils.privateDecrypt(userEntity.getPassword(),rsaKey));
            userEntity.setPassword(password);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        this.save(userEntity);

        return "注册成功";
    }
}