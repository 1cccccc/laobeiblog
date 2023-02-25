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


@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper,ArticleEntity> implements ArticleService {
}
