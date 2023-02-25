package com.xi.controller;

import com.xi.common.Result;
import com.xi.common.Utils;
import com.xi.entity.CategoryEntity;
import com.xi.service.CategoryService;
import com.xi.service.categoryService;
import com.xi.swagger.api.CategoryApi;
import com.xi.vo.CategoryVo;
import com.xi.vo.CategoryVo;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController implements CategoryApi {
    @Resource
    CategoryService categoryService;

    @Override
    @GetMapping("/count")
    public Result count() {
        long count = categoryService.count();
        return Result.success().setData(count);
    }

    @Override
    @GetMapping("/list")
    public Result list() {
        List<CategoryEntity> CategoryEntityList = categoryService.list();
        return Result.success().setData(CategoryEntityList);
    }

    @Override
    @PostMapping("/add")
    public Result add(@RequestBody CategoryVo vo) {
        CategoryEntity CategoryEntity = Utils.vochange(vo, new CategoryEntity());
        boolean b = categoryService.save(CategoryEntity);
        return Result.success().setData(b);
    }

    @Override
    @DeleteMapping("/remove/{id}")
    public Result remove(@PathVariable("id") int id) {
        boolean b = categoryService.removeById(id);
        return Result.success().setData(b);
    }

    @Override
    @PutMapping("/update")
    public Result update(@RequestBody CategoryVo vo) {
        CategoryEntity CategoryEntity = Utils.vochange(vo, new CategoryEntity());
        boolean b = categoryService.updateById(CategoryEntity);
        return Result.success().setData(b);
    }
}
