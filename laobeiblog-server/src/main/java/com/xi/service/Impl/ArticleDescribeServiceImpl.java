package com.xi.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xi.entity.ArticleDescribeEntity;
import com.xi.mapper.ArticleDescribeMapper;
import com.xi.service.ArticleDescribeService;
import org.springframework.stereotype.Service;

@Service
public class ArticleDescribeServiceImpl extends ServiceImpl<ArticleDescribeMapper, ArticleDescribeEntity> implements ArticleDescribeService {
}
