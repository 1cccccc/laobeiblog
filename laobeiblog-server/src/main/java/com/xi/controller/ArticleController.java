package com.xi.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.xi.common.PageReq;
import com.xi.common.PageUtil;
import com.xi.common.Result;
import com.xi.entity.ArticleEntity;
import com.xi.mapper.ArticleMapper;
import com.xi.service.ArticleService;
import com.xi.swagger.api.ArticleApi;
import com.xi.vo.ArticleVo;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/article")
public class ArticleController implements ArticleApi {
    @Resource
    private ArticleService articleService;

    @Override
    @GetMapping("/list")
    public Result list(PageReq pageReq){
        PageUtil pageUtil = articleService.queryList(pageReq.getPage(),pageReq.getSize());
        return Result.success().setData(pageUtil);
    }

    @Override
    @GetMapping("/get/{id}")
    public Result get(@PathVariable("id") int id){
        ArticleEntity article = articleService.getById(id);
        return Result.success().setData(article);
    }

    @Override
    @DeleteMapping("/remove/{id}")
    public Result remove(@PathVariable("id") int id) {
        articleService.update(new ArticleEntity(),new UpdateWrapper<ArticleEntity>().eq("article_id",id).set("deleted",1));
        return Result.success();
    }

    @Override
    @PostMapping("/add")
    public Result add(@RequestBody ArticleVo vo) {
        ArticleEntity articleEntity = new ArticleEntity();
        BeanUtils.copyProperties(vo,articleEntity);
        articleService.save(articleEntity);
        return Result.success();
    }

    @Override
    @PutMapping("/update")
    public Result update(@RequestBody ArticleVo vo) {
        ArticleEntity articleEntity = new ArticleEntity();
        BeanUtils.copyProperties(vo,articleEntity);

        UpdateWrapper<ArticleEntity> updateWrapper = new UpdateWrapper<ArticleEntity>().eq("article_id", vo.getArticleId());

        articleService.update(articleEntity,updateWrapper);
        return Result.success();
    }
}
