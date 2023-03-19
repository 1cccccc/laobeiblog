package com.xi.controller;

import com.xi.common.Result;
import com.xi.service.CodeService;
import com.xi.swagger.api.CodeApi;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/code")
public class CodeController implements CodeApi {

    @Resource
    CodeService codeService;

    @Override
    @GetMapping("/sendCode")
    public Result sendCode(String phone) {
        codeService.sendCode(phone);
        return Result.success();
    }

    @Override
    @GetMapping("/sendEmailCode")
    public Result sendEmailCode(String email) {
        codeService.sendEmailCode(email);
        return Result.success();
    }
}
