package com.xi.swagger.api;

import com.xi.common.PageReq;
import com.xi.common.Result;
import com.xi.vo.ArticleVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name="文章描述管理",description = "文章描述管理")
public interface ArticleDescribeApi {

    @Operation(summary = "文章描述列表",description = "文章描述列表")
    @ApiResponse(description = "返回数据",content = @Content(mediaType = "json/application",schema = @Schema(implementation = Result.class)))
    public Result list(@Parameter(description = "分页对象",schema = @Schema(implementation = PageReq.class)) PageReq pageReq);

    @Operation(summary = "根据id获取文章描述",description = "根据id获取文章描述")
    @ApiResponse(description = "返回数据",content = @Content(mediaType = "json/application",schema = @Schema(implementation = Result.class)))
    public Result get(@Parameter(description = "文章描述id") int id);

    @Operation(summary = "根据id删除文章描述",description = "根据id删除文章描述")
    @ApiResponse(description = "返回数据",content = @Content(mediaType = "json/application",schema = @Schema(implementation = Result.class)))
    public Result remove(@Parameter(description = "文章描述id") int id);

    @Operation(summary = "添加文章描述",description = "添加文章描述")
    @ApiResponse(description = "返回数据",content = @Content(mediaType = "json/application",schema = @Schema(implementation = Result.class)))
    public Result add(@Parameter(description = "文章描述Vo") ArticleVo vo);

    @Operation(summary = "修改文章",description = "修改文章")
    @ApiResponse(description = "返回数据",content = @Content(mediaType = "json/application",schema = @Schema(implementation = Result.class)))
    public Result update(@Parameter(description = "文章描述Vo")ArticleVo vo);
}
