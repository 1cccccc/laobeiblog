package com.xi.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.xi.common.Result;
import com.xi.common.Utils;
import com.xi.entity.CommentEntity;
import com.xi.service.CommentService;
import com.xi.swagger.api.CommentApi;
import com.xi.vo.CommonVo;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/common")
public class CommonController implements CommentApi {
    @Resource
    CommentService commentService;

    @Override
    @GetMapping("/list/{articleid}")
    public Result list(@PathVariable("articleid") int articleid) {
        QueryWrapper<CommentEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("article_id",articleid);
        List<CommentEntity> list = commentService.list(queryWrapper);
        return Result.success().setData(list);
    }

    @Override
    @PostMapping("/add")
    public Result add(@RequestBody CommonVo vo) {
        CommentEntity commentEntity = Utils.vochange(vo, new CommentEntity());
        boolean b = commentService.save(commentEntity);
        return Result.success().setData(b);
    }

    @Override
    @DeleteMapping("/remove/{id}")
    public Result remove(@PathVariable("id") int id) {
        UpdateWrapper<CommentEntity> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("comment_id",id).set("deleted",1);
        boolean b = commentService.update(new CommentEntity(), updateWrapper);
        return Result.success().setData(b);
    }

    @Override
    @PutMapping("/update")
    public Result update(@RequestBody CommonVo vo) {
        CommentEntity commentEntity = Utils.vochange(vo, new CommentEntity());
        boolean b = commentService.updateById(commentEntity);
        return Result.success().setData(b);
    }
}
