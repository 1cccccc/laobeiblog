package com.xi.swagger.api;

import com.xi.common.PageReq;
import com.xi.common.Result;
import com.xi.entity.ArticleEntity;
import com.xi.vo.ArticleVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "文章管理",description = "文章管理")
public interface ArticleApi {

    @Operation(summary = "获取所有文章",description = "获取所有文章，可分页")
    @ApiResponse(description = "返回数据",content = @Content(mediaType = "application/json",schema = @Schema(implementation = Result.class)))
    public Result list(@Parameter(description = "分页对象",schema = @Schema(implementation = PageReq.class))PageReq pageReq);

    @Operation(summary = "搜索文章",description = "搜索文章")
    @ApiResponse(description = "返回数据",content = @Content(mediaType = "application/json",schema = @Schema(implementation = Result.class)))
    public Result search(@Parameter(description = "搜索条件，可匹配文章标题和文章内容") String searchCriteria);

    @Operation(summary = "根据id获取文章",description = "根据id获取文章")
    @ApiResponse(description = "返回数据",content = @Content(mediaType = "application/json",schema = @Schema(implementation = Result.class)))
    public Result get(@Parameter(description = "文章id") int id);

    @Operation(summary = "根据id删除文章",description = "根据id删除文章")
    @ApiResponse(description = "返回数据",content = @Content(mediaType = "application/json",schema = @Schema(implementation = Result.class)))
    public Result remove(@Parameter(description = "文章id") int id);

    @Operation(summary = "添加文章",description = "添加文章")
    @ApiResponse(description = "返回数据",content = @Content(mediaType = "application/json",schema = @Schema(implementation = Result.class)))
    public Result add(@Parameter(description = "文章Vo")ArticleVo vo);

    @Operation(summary = "修改文章",description = "修改文章")
    @ApiResponse(description = "返回数据",content = @Content(mediaType = "application/json",schema = @Schema(implementation = Result.class)))
    public Result update(@Parameter(description = "文章Vo")ArticleVo vo);
}
