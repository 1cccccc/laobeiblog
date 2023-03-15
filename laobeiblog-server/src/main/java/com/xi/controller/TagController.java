package com.xi.controller;

import com.xi.common.Result;
import com.xi.common.Utils;
import com.xi.entity.TagEntity;
import com.xi.service.TagService;
import com.xi.swagger.api.TagApi;
import com.xi.vo.TagVo;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tag")
public class TagController implements TagApi {
    @Resource
    TagService tagService;

    @Override
    @GetMapping("/count")
    public Result count() {
        long count = tagService.count();
        return Result.success().setData(count);
    }

    @Override
    @GetMapping("/all")
    public Result all() {
        List<TagEntity> tagEntityList = tagService.list();
        return Result.success().setData(tagEntityList);
    }

    @Override
    @GetMapping("/getList")
    public Result getList(String ids) {
        List<TagEntity> tagEntities=tagService.getList(ids);

        return Result.success().setData(tagEntities);
    }

    @Override
    @PostMapping("/add")
    public Result add(@RequestBody TagVo vo) {
        TagEntity tagEntity = Utils.vochange(vo, new TagEntity());
        boolean b = tagService.save(tagEntity);
        return Result.success().setData(b);
    }

    @Override
    @DeleteMapping("/remove/{id}")
    public Result remove(@PathVariable("id") int id) {
        boolean b = tagService.removeById(id);
        return Result.success().setData(b);
    }

    @Override
    @PutMapping("/update")
    public Result update(@RequestBody TagVo vo) {
        TagEntity tagEntity = Utils.vochange(vo, new TagEntity());
        boolean b = tagService.updateById(tagEntity);
        return Result.success().setData(b);
    }
}
