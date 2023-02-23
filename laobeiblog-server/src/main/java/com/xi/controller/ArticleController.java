package com.xi.controller;

import com.xi.common.PageUtil;
import com.xi.common.Result;
import com.xi.service.ArticleService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/article")
@RestController
public class ArticleController {
    @Resource
    private ArticleService articleService;

    @GetMapping("/queryList")
    public Result queryList(){
        PageUtil pageUtil = articleService.queryList();
        return Result.success().setData(pageUtil);
    }
}
