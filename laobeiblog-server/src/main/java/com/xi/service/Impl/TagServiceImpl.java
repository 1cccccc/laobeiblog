package com.xi.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xi.entity.TagEntity;
import com.xi.mapper.TagMapper;
import com.xi.service.TagService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, TagEntity> implements TagService {
    @Override
    public List<TagEntity> getList(String ids) {
        String[] strings = ids.split(",");
        List<Integer> collect = Arrays.stream(strings).map(item -> Integer.parseInt(item)).collect(Collectors.toList());
        List<TagEntity> tagEntities = this.list(new LambdaQueryWrapper<TagEntity>().in(TagEntity::getTagId, collect));

        return tagEntities;
    }
}
