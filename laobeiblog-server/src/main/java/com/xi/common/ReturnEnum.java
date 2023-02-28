package com.xi.common;

public enum ReturnEnum {
    SUCCESS(200,"success"),ERROR(400,"error");

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
