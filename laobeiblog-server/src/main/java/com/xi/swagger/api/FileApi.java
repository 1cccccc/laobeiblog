package com.xi.swagger.api;

import com.xi.common.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.multipart.MultipartFile;

@Tag(name="文件管理",description = "文件管理")
public interface FileApi {
    @Operation(summary = "获取oss policy和签名信息",description = "获取oss policy和签名信息")
    @ApiResponse(description = "返回policy数据",content = @Content(mediaType = "application/json",schema = @Schema(implementation = Result.class)))
    public Result policy();

    @Operation(summary = "文件上传",description = "文件上传")
    @ApiResponse(description = "返回数据",content = @Content(mediaType = "application/json",schema = @Schema(implementation = Result.class)))
    public Result simpleManyUpload(@Parameter(description = "所属用户id")int id,@Parameter(description = "文件列表",array = @ArraySchema(schema = @Schema(implementation = MultipartFile.class))) MultipartFile[] files);

    @Operation(summary = "获取指定用户的文件列表",description = "获取指定用户的文件")
    @ApiResponse(description = "返回数据",content = @Content(mediaType = "application/json",schema = @Schema(implementation = Result.class)))
    public Result getFileList(@Parameter(description = "所属用户id")int userId);

    @Operation(summary = "根据文件md5值删除文件",description = "根据文件md5值删除文件")
    @ApiResponse(description = "返回数据",content = @Content(mediaType = "application/json",schema = @Schema(implementation = Result.class)))
    public Result removeFiles(@Parameter(description = "文件md5值")String[] md5s);
}
