package com.xi.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("article")
@Accessors(chain = true)
public class ArticleEntity implements Serializable {
    @TableField(exist = false)
    private static final long  serialVersionUID=1L;

    @TableId(value = "article_id")
    private Integer articleId;
    private String articleTitle;
    private String articleContent;
    private String coverUrl;
    private String articleSummary;
    private Integer isPrivate;
    private Integer isTop;
    private Integer isOriginal;
    private String forwardUrl;
    @TableField(value = "`status`")
    private Integer status;
    @TableField(value = "`like`")
    private Integer like;
    @TableField(value = "`read`")
    private Integer read;
    private Integer tagId;
    private Integer categoryId;
    @TableField(value = "create_time",fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    private Integer deleted;
}
