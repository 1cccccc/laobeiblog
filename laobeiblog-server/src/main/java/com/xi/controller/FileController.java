package com.xi.controller;

import com.xi.common.Result;
import com.xi.service.FileService;
import com.xi.swagger.api.FileApi;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public Result upload() {
        return null;
    }
}
