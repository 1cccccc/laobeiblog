package com.xi.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TagVo implements Serializable,Vo{
    private final static long SerialVersionUID=1L;

    @Schema(description = "标签id")
    private Integer tagId;

    @Schema(description = "标签名")
    private String tagName;

    @Schema(description = "标签拥有的文章数量")
    private Integer articleNum;

    @Schema(description = "所属用户id")
    private Integer userId;
}
