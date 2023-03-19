package com.xi.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xi.common.PageReq;
import com.xi.common.PageUtil;
import com.xi.common.Result;
import com.xi.common.Utils;
import com.xi.entity.ArticleEntity;
import com.xi.service.ArticleService;
import com.xi.swagger.api.ArticleApi;
import com.xi.vo.ArticleVo;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/article")
public class ArticleController implements ArticleApi {
    @Resource
    private ArticleService articleService;

    @Override
    @GetMapping("/list")
    public Result list(PageReq pageReq){
        Page<ArticleEntity> page = articleService.page(new Page<ArticleEntity>(pageReq.getPage(), pageReq.getSize()));
        return Result.success().setData(new PageUtil(page));
    }

    @Override
    @GetMapping("/search/{searchCriteria}")
    public Result search(@PathVariable("searchCriteria") String searchCriteria){
        QueryWrapper<ArticleEntity> queryWrapper = new QueryWrapper<ArticleEntity>()
                .like("article_title", searchCriteria)
                .or()
                .like("article_content", searchCriteria);
        List<ArticleEntity> list = articleService.list(queryWrapper);
        return Result.success().setData(list);
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
        boolean b = articleService.update(new ArticleEntity(), new UpdateWrapper<ArticleEntity>().eq("article_id", id).set("deleted", 1));
        return Result.success().setData(b);
    }

    @Override
    @PostMapping("/add")
    public Result add(@RequestBody ArticleVo vo) {
        ArticleEntity articleEntity = Utils.vochange(vo, new ArticleEntity());
        boolean b = articleService.save(articleEntity);
        return Result.success().setData(b);
    }

    @Override
    @PutMapping("/update")
    public Result update(@RequestBody ArticleVo vo) {
        ArticleEntity articleEntity = Utils.vochange(vo, new ArticleEntity());
        boolean b = articleService.updateById(articleEntity);
        return Result.success().setData(b);
    }
}
