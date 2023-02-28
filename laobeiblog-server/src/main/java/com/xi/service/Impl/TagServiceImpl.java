package com.xi.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xi.entity.TagEntity;
import com.xi.mapper.TagMapper;
import com.xi.service.TagService;
import org.springframework.stereotype.Service;

@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, TagEntity> implements TagService {
}
