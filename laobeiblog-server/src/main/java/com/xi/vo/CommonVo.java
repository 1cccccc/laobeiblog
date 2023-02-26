package com.xi.vo;

import io.swagger.v3.oas.annotations.media.Schema;
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
public class CommonVo implements Serializable,Vo {
    private final static long serialVersionUID=1L;

    @Schema(description = "评论id")
    private Integer commentId;

    @Schema(description = "所属用户id")
    private Integer userId;

    @Schema(description = "所属文章id")
    private Integer articleId;

    @Schema(description = "评论内容")
    private String commentContent;

    @Schema(description = "父评论id")
    private Integer parentId;

    @Schema(description = "删除时间")
    private Date removeTime;
}
