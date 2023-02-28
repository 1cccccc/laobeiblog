package com.xi.swagger.api;

import com.xi.common.Result;
import com.xi.vo.CategoryVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "分类管理", description = "分类管理")
public interface CategoryApi {
    @Operation(summary = "获取分类数", description = "获取分类数")
    @ApiResponse(description = "返回数据", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Result.class)))
    public Result count();

    @Operation(summary = "分类列表,直接返回全部分类不支持分页", description = "分类列表,直接返回全部分类不支持分页")
    @ApiResponse(description = "返回数据", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Result.class)))
    public Result list();


    @Operation(summary = "增加分类", description = "增加分类")
    @ApiResponse(description = "返回数据", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Result.class)))
    public Result add(@Parameter(description = "分类vo", schema = @Schema(implementation = CategoryVo.class)) CategoryVo  vo);

    @Operation(summary = "获取分类数", description = "获取分类数")
    @ApiResponse(description = "返回数据", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Result.class)))
    public Result remove(@Parameter(description = "分类id") int id);

    @Operation(summary = "获取分类数", description = "获取分类数")
    @ApiResponse(description = "返回数据", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Result.class)))
    public Result update(@Parameter(description = "分类vo", schema = @Schema(implementation = CategoryVo.class)) CategoryVo  vo);
}
