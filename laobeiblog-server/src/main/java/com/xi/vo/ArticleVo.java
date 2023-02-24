package com.xi.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ArticleVo implements Serializable {
    private static final long  serialVersionUID=1L;

    @Schema(description = "文章id")
    private Integer articleId;

    @Schema(description = "文章标题")
    private String articleTitle;

    @Schema(description = "文章内容，html格式")
    private String articleContent;

    @Schema(description = "文章封面图片地址")
    private String coverUrl;

    @Schema(description = "文章简介")
    private String articleSummary;

    @Schema(description = "是否私有，1私有，2公有")
    private Integer isPrivate;

    @Schema(description = "是否置顶，1置顶，2不置顶")
    private Integer isTop;

    @Schema(description = "是否原创，0原创，1转载")
    private Integer isOriginal;

    @Schema(description = "转发地址")
    private String forwardUrl;

    @Schema(description = "文章状态，0下架，1上架，2草稿")
    private Integer status;

    @Schema(description = "点赞数")
    private Integer like;

    @Schema(description = "阅读数")
    private Integer read;

    @Schema(description = "所属标签id")
    private Integer tagId;

    @Schema(description = "所属分类id")
    private Integer categoryId;
}
