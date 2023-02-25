package com.xi.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("category")
public class CategoryEntity implements Serializable {
    @TableField(exist = false)
    private final static long SerialVersionUID=1L;

    @TableId(value = "category_id")
    @Schema(description = "分类id")
    private Integer categoryId;

    @Schema(description = "分类名")
    private String categoryName;

    @Schema(description = "分类下有多少文章")
    private Integer articleNum;

    @Schema(description = "所属用户id")
    private Integer userId;

    @TableField(value = "create_time",fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(value = "update_time",fill=FieldFill.INSERT_UPDATE)
    private Date updateTime;
}
