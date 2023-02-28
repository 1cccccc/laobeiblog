package com.xi.swagger.api;

import com.xi.common.Result;
import com.xi.vo.CommonVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name="评论管理",description = "评论管理")
public interface CommentApi {
    @Operation(summary = "根据文章id返回评论列表",description = "根据文章id返回评论列表")
    @ApiResponse(description = "返回数据",content = @Content(mediaType = "application/json",schema = @Schema(implementation = Result.class)))
    public Result list(@Parameter(description = "评论vo")int articleid);

    @Operation(summary = "增加评论",description = "增加评论")
    @ApiResponse(description = "返回数据",content = @Content(mediaType = "application/json",schema = @Schema(implementation = Result.class)))
    public Result add(@Parameter(description = "评论vo",schema = @Schema(implementation = CommonVo.class)) CommonVo vo);

    @Operation(summary = "删除评论",description = "删除评论")
    @ApiResponse(description = "返回数据",content = @Content(mediaType = "application/json",schema = @Schema(implementation = Result.class)))
    public Result remove(@Parameter(description = "评论id") int id);

    @Operation(summary = "更改评论信息",description = "更改评论信息")
    @ApiResponse(description = "返回数据",content = @Content(mediaType = "application/json",schema = @Schema(implementation = Result.class)))
    public Result update(@Parameter(description = "评论vo",schema = @Schema(implementation = CommonVo.class)) CommonVo vo);
}
