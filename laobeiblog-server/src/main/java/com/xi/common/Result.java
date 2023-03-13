package com.xi.common;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashMap;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result implements Serializable {
    private static final long SerialVersionUID=1L;
    @Schema(description = "状态码")
    private Integer code;
    @Schema(description = "状态消息")
    private String message;
    @Schema(description = "返回对象")
    private Object data;

    public static Result success(){
        Result result = new Result();
        result.code=ReturnEnum.SUCCESS.getCode();
        result.message=ReturnEnum.SUCCESS.getMessage();

        return result;
    }
    public static Result success(Integer code,String message){
        Result result = new Result();
        result.code=code;
        result.message=message;

        return result;
    }

    public static Result error(){
        Result result = new Result();
        result.code=ReturnEnum.ERROR.getCode();
        result.message=ReturnEnum.ERROR.getMessage();

        return result;
    }

    public static Result error(Integer code,String message){
        Result result = new Result();
        result.code=code;
        result.message=message;

        return result;
    }

    public Result setData(Object data) {
        this.data = data;
        return this;
    }
}
