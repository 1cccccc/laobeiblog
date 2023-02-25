package com.xi.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xi.common.PageReq;
import com.xi.common.PageUtil;
import com.xi.common.Result;
import com.xi.common.Utils;
import com.xi.entity.ArticleDescribeEntity;
import com.xi.service.ArticleDescribeService;
import com.xi.swagger.api.ArticleDescribeApi;
import com.xi.vo.ArticleDescribeVo;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/articledescribe")
public class ArticleDescribeController implements ArticleDescribeApi {
    @Resource
    private ArticleDescribeService articleDescribeService;

    @Override
    @GetMapping("/list")
    public Result list(PageReq pageReq) {
        Page<ArticleDescribeEntity> page = articleDescribeService.page(new Page<ArticleDescribeEntity>(pageReq.getPage(), pageReq.getSize()));
        return Result.success().setData(new PageUtil(page));
    }

    @Override
    @GetMapping ("/get/{id}")
    public Result get(@PathVariable("id") int id) {
        ArticleDescribeEntity entity = articleDescribeService.getById(id);
        return Result.success().setData(entity);
    }

    @Override
    @DeleteMapping("/remove/{id}")
    public Result remove(@PathVariable("id")int id) {
        return Result.success().setData(articleDescribeService.removeById(id));
    }

    @Override
    @PostMapping("/add")
    public Result add(@RequestBody ArticleDescribeVo vo) {
        ArticleDescribeEntity articleDescribeEntity = Utils.vochange(vo, new ArticleDescribeEntity());

        boolean b = articleDescribeService.save(articleDescribeEntity);
        return Result.success().setData(b);
    }

    @Override
    @PutMapping("/update")
    public Result update(@RequestBody ArticleDescribeVo vo) {
        ArticleDescribeEntity articleDescribeEntity = Utils.vochange(vo, new ArticleDescribeEntity());
        boolean b = articleDescribeService.updateById(articleDescribeEntity);
        return Result.success().setData(b);
    }
}
