package com.xi.controller;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.nimbusds.jose.JOSEException;
import com.xi.common.Result;
import com.xi.common.Utils;
import com.xi.entity.UserEntity;
import com.xi.security.JwtUtils;
import com.xi.service.UserService;
import com.xi.swagger.api.UserApi;
import com.xi.vo.UserVo;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/user")
public class UserController implements UserApi {
    @Resource
    UserService userService;

    @Override
    @PostMapping("/add")
    public Result add(@RequestBody UserVo vo) {
        UserEntity userEntity = Utils.vochange(vo, new UserEntity());
        userEntity.setLastLoginTime(new Date());

        boolean b = userService.save(userEntity);

        return Result.success().setData(b);
    }

    @Override
    @DeleteMapping("/remove")
    public Result remove(@RequestBody int id) {
        boolean b = userService.update(new UserEntity(), new UpdateWrapper<UserEntity>().eq("user_id", id).set("deleted", 1));
        return Result.success().setData(b);
    }

    @Override
    @PutMapping("/update")
    public Result update(@RequestBody UserVo vo) {
        UserEntity userEntity = Utils.vochange(vo, new UserEntity());

        boolean b = userService.updateById(userEntity);

        return Result.success().setData(b);
    }

    @Override
    @PostMapping("/login")
    public Result login(@RequestBody UserVo vo) {
        UserEntity userEntity = Utils.vochange(vo, new UserEntity());
        String token = userService.login(userEntity);

        return Result.success().setData(token);
    }
}
