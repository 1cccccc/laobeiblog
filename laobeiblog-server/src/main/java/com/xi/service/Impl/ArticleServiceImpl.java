package com.xi.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.Query;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xi.common.PageUtil;
import com.xi.entity.ArticleEntity;
import com.xi.mapper.ArticleMapper;
import com.xi.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper,ArticleEntity> implements ArticleService {
    @Override
    public PageUtil queryList() {
        Page<ArticleEntity> page = this.page(new Page<>(1,10));
        log.info(String.valueOf(page.getRecords()));

        return new PageUtil(page);
    }
}
