package com.xi.vo;

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
public class CategoryVo implements Serializable,Vo {
    private final static long SerialVersionUID=1L;

    @Schema(description = "分类id")
    private Integer categoryId;

    @Schema(description = "分类名")
    private String categoryName;

    @Schema(description = "分类下有多少文章")
    private Integer articleNum;

    @Schema(description = "所属用户id")
    private Integer userId;
}
