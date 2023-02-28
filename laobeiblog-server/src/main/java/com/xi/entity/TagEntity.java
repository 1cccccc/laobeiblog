package com.xi.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("tag")
public class TagEntity implements Serializable {
    @TableField(exist = false)
    private final static long SerialVersionUID=1L;
    @TableId("tag_id")
    private Integer tagId;
    private String tagName;
    private Integer articleNum;
    private Integer userId;

    @TableField(value = "create_time",fill = FieldFill.INSERT)
    private Date createTime;
}
