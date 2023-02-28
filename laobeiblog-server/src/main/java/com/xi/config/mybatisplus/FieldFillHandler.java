package com.xi.config.mybatisplus;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
public class FieldFillHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("insertFill");
        this.strictInsertFill(metaObject,"createTime",Date.class,new Date());
        this.strictInsertFill(metaObject,"updateTime",Date.class,new Date());
        this.strictInsertFill(metaObject,"deleted",Integer.class,0);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("updateFill");
        this.strictInsertFill(metaObject,"updateTime",Date.class,new Date());
    }
}
