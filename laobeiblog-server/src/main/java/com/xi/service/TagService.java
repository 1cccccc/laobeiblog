package com.xi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xi.entity.TagEntity;

import java.util.List;

public interface TagService extends IService<TagEntity> {
    List<TagEntity> getList(String ids);
}
