package com.xi.swagger.api;

import com.xi.common.Result;
import com.xi.vo.TagVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "标签管理",description = "标签管理")
public interface TagApi {

    @Operation(summary = "获取标签数",description = "获取标签数")
    @ApiResponse(description = "返回数据",content = @Content(mediaType = "application/json",schema = @Schema(implementation = Result.class)))
    public Result count();

    @Operation(summary = "标签列表,直接返回全部标签不支持分页",description = "标签列表,直接返回全部标签不支持分页")
    @ApiResponse(description = "返回数据",content = @Content(mediaType = "application/json",schema = @Schema(implementation = Result.class)))
    public Result list();


    @Operation(summary = "增加标签",description = "增加标签")
    @ApiResponse(description = "返回数据",content = @Content(mediaType = "application/json",schema = @Schema(implementation = Result.class)))
    public Result add(@Parameter(description = "标签vo",schema = @Schema(implementation = TagVo.class)) TagVo vo);

    @Operation(summary = "获取标签数",description = "获取标签数")
    @ApiResponse(description = "返回数据",content = @Content(mediaType = "application/json",schema = @Schema(implementation = Result.class)))
    public Result remove(@Parameter(description = "标签id") int id);

    @Operation(summary = "获取标签数",description = "获取标签数")
    @ApiResponse(description = "返回数据",content = @Content(mediaType = "application/json",schema = @Schema(implementation = Result.class)))
    public Result update(@Parameter(description = "标签vo",schema = @Schema(implementation = TagVo.class)) TagVo vo);
}
