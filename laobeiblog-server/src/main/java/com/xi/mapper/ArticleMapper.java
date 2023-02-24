package com.xi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xi.entity.ArticleEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ArticleMapper extends BaseMapper<ArticleEntity> {
}
