package com.xi.vo;

import com.baomidou.mybatisplus.annotation.TableId;
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
public class ArticleDescribeVo implements Serializable,Vo {
    private final static long serialVersionUID=1L;

    @Schema(description = "文章id")
    private int articleId;

    @Schema(description = "文章标题")
    private String articleTitle;

    @Schema(description = "是否置顶，1置顶，2不置顶")
    private int isTop;

    @Schema(description = "所属标签id")
    private int tagId;

    @Schema(description = "文章简介")
    private String articleDescribe;

    @Schema(description = "所属分类id")
    private int categoryId;
}
