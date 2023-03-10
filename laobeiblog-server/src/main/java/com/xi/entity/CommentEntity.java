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
@TableName("comment")
public class CommentEntity implements Serializable {
    @TableField(exist = false)
    private final static long serialVersionUID=1L;
    @TableId("comment_id")
    private Integer commentId;
    private Integer userId;
    private Integer articleId;
    private String commentContent;
    private Integer parentId;
    private Integer deleted;
    private Date removeTime;
    @TableField(value = "create_time",fill = FieldFill.INSERT)
    private Date createTime;
}
