package com.xi.controller;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.xi.common.Result;
import com.xi.common.Utils;
import com.xi.entity.UserEntity;
import com.xi.service.UserService;
import com.xi.swagger.api.UserApi;
import com.xi.vo.UserVo;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController implements UserApi {
    @Resource
    UserService userService;

    @Override
    @PostMapping("/register")
    public Result register(@Valid @RequestBody UserVo vo) {
        UserEntity userEntity = Utils.vochange(vo, new UserEntity());
        userEntity.setLastLoginTime(new Date());

        String s = userService.register(userEntity);

        return Result.success().setData(s);
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
    @GetMapping("/otherInfo")
    public Result otherInfo(int id) {
        Map<String,Object> map=userService.otherInfo(id);

        return Result.success().setData(map);
    }

    @Override
    @PostMapping("/login")
    public Result login(@RequestBody UserVo vo) {
        UserEntity userEntity = Utils.vochange(vo, new UserEntity());
        Map<String,Object> data = userService.login(userEntity);

        return Result.success().setData(data);
    }

    @Override
    @GetMapping("/loginout")
    public Result loginOut() {
        boolean b = userService.loginOut();
        return Result.success().setData(b);
    }
}
