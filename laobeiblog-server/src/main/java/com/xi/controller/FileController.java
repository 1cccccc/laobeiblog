package com.xi.controller;

import com.xi.common.Result;
import com.xi.service.FileService;
import com.xi.swagger.api.FileApi;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/file")
public class FileController implements FileApi {
    @Resource
    FileService fileService;

    @Override
    @GetMapping("/policy")
    public Result policy() {
        Map<String,String> policy = fileService.policy();
        return Result.success().setData(policy);
    }

    @Override
    @PostMapping("/simpleManyUpload/{userId}")
    public Result simpleManyUpload(@PathVariable("userId") int userId,@RequestBody() MultipartFile[] files) {
        List<Map<String, String>> mapList = fileService.simpleManyUpload(userId, files);
        return Result.success().setData(mapList);
    }

    @Override
    @GetMapping("/getFileList/{userId}")
    public Result getFileList(@PathVariable("userId") int userId) {
        return Result.success().setData(fileService.getFileList(userId));
    }

    @Override
    public Result getFileList(String md5) {
        boolean b = fileService.removeFile(md5);
        if(!b){
            return Result.error();
        }
        return Result.success();
    }

}
