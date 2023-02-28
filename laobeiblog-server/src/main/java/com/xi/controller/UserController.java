package com.xi.controller;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.xi.common.Result;
import com.xi.common.Utils;
import com.xi.entity.UserEntity;
import com.xi.service.UserService;
import com.xi.swagger.api.UserApi;
import com.xi.vo.UserVo;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/user")
public class UserController implements UserApi {
    @Resource
    UserService userService;

    @Override
    public Result add(UserVo vo) {
        UserEntity userEntity = Utils.vochange(vo, new UserEntity());
        userEntity.setLastLoginTime(new Date());

        boolean b = userService.save(userEntity);

        return Result.success().setData(b);
    }

    @Override
    public Result remove(int id) {
        boolean b = userService.update(new UserEntity(), new UpdateWrapper<UserEntity>().eq("user_id", id).set("deleted", 1));
        return Result.success().setData(b);
    }

    @Override
    public Result update(UserVo vo) {
        UserEntity userEntity = Utils.vochange(vo, new UserEntity());

        boolean b = userService.updateById(userEntity);

        return Result.success().setData(b);
    }
}
