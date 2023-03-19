package com.xi.exception;


import com.xi.common.Result;
import com.xi.common.ReturnEnum;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//需要捕获那个包下的异常
@RestControllerAdvice(basePackages = "com.xi.controller")
public class MyExceptionConfig {

    //对应的异常的处理（参数传值）
    @ExceptionHandler(BindException.class)
    public String validatedBindException(BindException e) {
        String message = e.getAllErrors().get(0).getDefaultMessage();
        return message;
    }

    //对应的异常的处理（json传值）
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result catchBingBindException(MethodArgumentNotValidException e){
        String message = e.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        return Result.error(ReturnEnum.ARGUMENTNOTVALID.getCode(), ReturnEnum.ARGUMENTNOTVALID.getMessage());
    }
}