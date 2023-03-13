package com.xi.controller;

import com.xi.common.Result;
import com.xi.common.ReturnEnum;
import com.xi.service.FileService;
import com.xi.swagger.api.FileApi;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
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
    @DeleteMapping("/removeFiles")
    public Result removeFiles(@RequestBody String[] md5s) {
        List<Boolean> bs = fileService.removeFiles(md5s);
        List<String> ls=new ArrayList<>();
        for (int i = 0; i < bs.size(); i++) {
            if(!bs.get(i)){
                ls.add(md5s[i]);
            }
        }
        if(ls.size()<=0){
            return Result.success();
        }
        return Result.success(ReturnEnum.NOTALLSUCCESSED.getCode(),ReturnEnum.NOTALLSUCCESSED.getMessage()).setData(ls);
    }
}
