package com.xi.swagger.api;

import com.xi.common.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "验证管理",description = "验证管理")
public interface CodeApi {


    @Operation(summary = "发送短信，该接口为异步接口",description = "发送短信，该接口为异步接口")
    @ApiResponse(description = "返回数据",content = @Content(mediaType = "application/json",schema = @Schema(implementation = Result.class)))
    public Result sendCode(@Parameter(description = "电话号码") String phone);

    @Operation(summary = "发送邮件验证码，该接口为异步接口",description = "发送邮件验证码，该接口为异步接口")
    @ApiResponse(description = "返回数据",content = @Content(mediaType = "application/json",schema = @Schema(implementation = Result.class)))
    public Result sendEmailCode(@Parameter(description = "邮箱") String email);

    @Operation(summary = "获取二维码",description = "获取二维码")
    @ApiResponse(description = "返回数据",content = @Content(mediaType = "application/json",schema = @Schema(implementation = Result.class)))
    public Result getQRCode(@Parameter(description = "二维码内容") String content);
}
