package com.xi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xi.common.PageUtil;
import com.xi.entity.ArticleEntity;

public interface ArticleService extends IService<ArticleEntity> {
    public PageUtil queryList();
}
