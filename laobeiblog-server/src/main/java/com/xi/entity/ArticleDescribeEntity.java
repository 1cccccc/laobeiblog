package com.xi.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@TableName("article_describe")
public class ArticleDescribeEntity implements Serializable {
    @TableField(exist = false)
    private final static long serialVersionUID=1L;

    @TableId(value = "article_id",type = IdType.AUTO)
    private Integer articleId;

    private String articleTitle;

    private String coverUrl;

    private Integer isTop;

    private Integer tagId;
    private Integer userId;

    private String articleDescribe;

    private Integer categoryId;

    @TableField(value = "create_time",fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
}
