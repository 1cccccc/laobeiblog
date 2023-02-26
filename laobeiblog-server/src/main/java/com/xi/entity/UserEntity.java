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
@TableName("user")
public class UserEntity implements Serializable {
    @TableField(exist = false)
    private final static long serialVersionUID=1L;

    @TableId("user_id")
    private Integer userId;

    private String nickname;

    private String password;

    private String phone;

    private String email;

    private Integer loginType;

    private String loginIp;

    private String loginIpAddress;

    private String loginOs;

    private String regIpAddress;

    private Date lastLoginTime;

    private Integer deleted;

    @TableField(value = "create_time",fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
}
