package com.xi.swagger.api;

import com.xi.common.Result;
import com.xi.vo.UserVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "用户管理",description = "用户管理")
public interface UserApi {
    @Operation(summary = "增加用户",description = "增加用户")
    @ApiResponse(description = "返回数据",content = @Content(mediaType = "application/json",schema = @Schema(implementation = Result.class)))
    public Result add(@Parameter(description = "用户vo",schema = @Schema(implementation = UserVo.class)) UserVo vo);

    @Operation(summary = "删除用户",description = "删除用户")
    @ApiResponse(description = "返回数据",content = @Content(mediaType = "application/json",schema = @Schema(implementation = Result.class)))
    public Result remove(@Parameter(description = "用户id") int id);

    @Operation(summary = "更改用户信息",description = "更改用户信息")
    @ApiResponse(description = "返回数据",content = @Content(mediaType = "application/json",schema = @Schema(implementation = Result.class)))
    public Result update(@Parameter(description = "用户vo",schema = @Schema(implementation = UserVo.class)) UserVo vo);

    @Operation(summary = "登录接口",description = "登录接口")
    @ApiResponse(description = "返回数据",content = @Content(mediaType = "application/json",schema = @Schema(implementation = Result.class)))
    public Result login(@Parameter(description = "用户vo",schema = @Schema(implementation = UserVo.class)) UserVo vo);

    @Operation(summary = "退出登录接口",description = "退出登录接口")
    @ApiResponse(description = "返回数据",content = @Content(mediaType = "application/json",schema = @Schema(implementation = Result.class)))
    public Result loginOut();
}
