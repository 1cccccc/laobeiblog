package com.xi.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class UserVo implements Serializable,Vo {
    private final static long serialVersionUID=1L;
    @Schema(description = "用户id")
    private Integer userId;

    @Schema(description = "用户昵称")
    @Size(max = 20,min = 0,message = "nickname参数长度只能在0-20之间")
    private String nickname;

    @Schema(description = "用户账号")
    @NotBlank(message = "缺少username参数")
    @Size(max = 20,min = 0,message = "username参数长度只能在0-20之间")
    private String username;

    @Schema(description = "用户密码,使用rsa公钥进行加密的")
    @NotBlank(message = "缺少password参数")
    private String password;

    @Schema(description = "用户电话")
    private String phone;

    @Schema(description = "用户邮箱")
    @Email(message = "邮箱格式不正确")
    private String email;

    @Schema(description = "登录方式，1为username，2为二维码")
    private Integer loginType;

    @Schema(description = "登录时ip")
    private String loginIp;

    @Schema(description = "登录时ip所在地址")
    private String loginIpAddress;

    @Schema(description = "登录时使用系统")
    private String loginOs;

    @Schema(description = "注册时ip地址")
    private String regIpAddress;
}
