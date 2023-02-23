package com.xi.common;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class PageUtil implements Serializable  {
    private static final long SerialVersionUID=1L;
    private Long totalCount;//数据总数
    private Long totalPage;//数据总页数
    private Long pageSize;//每页多少条数据
    private Long currentPage;//当前多少页
    private List<?> list;//数据

    public PageUtil(IPage<?> page) {
        this.totalCount=page.getTotal();
        this.totalPage=page.getPages();
        this.pageSize=page.getSize();
        this.currentPage=page.getCurrent();

        this.list=page.getRecords();
    }
}
