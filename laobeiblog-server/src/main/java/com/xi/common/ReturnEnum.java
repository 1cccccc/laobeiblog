package com.xi.common;

public enum ReturnEnum {
    SUCCESS(200,"success"),
    ERROR(400,"error"),
    NOTALLSUCCESSED(206,"有部分文件未删除成功，请稍后重试"),
    ACCESSDENIED(403,"拒绝访问受保护的资源，您没有该权限"),
    AUTHENTICATION_FAILD(403,"认证不通过"),
    ARGUMENTNOTVALID(10001,"参数校验错误");

    private Integer code;
    private String message;

    ReturnEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
