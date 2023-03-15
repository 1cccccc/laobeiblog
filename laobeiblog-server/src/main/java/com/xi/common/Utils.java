package com.xi.common;

import com.xi.vo.Vo;
import org.springframework.beans.BeanUtils;

public class Utils {
    //vo属性对拷到实体类方法
    public static <T> T vochange(Vo source, T target){
        BeanUtils.copyProperties(source,target);
        return target;
    }
}
