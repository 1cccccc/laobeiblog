package com.xi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xi.common.PageUtil;
import com.xi.entity.ArticleEntity;
import lombok.extern.slf4j.Slf4j;

public interface ArticleService extends IService<ArticleEntity> {
    public PageUtil queryList(long pagenum,long size);
}
