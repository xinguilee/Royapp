package com.demo.smart.utils;

import lombok.Getter;

@Getter
public enum ResultEnum {
    SUCCESS(200, "success"),
    FAIL(500, "error"),
    NULL_ATTR(101, "属性为空");

    private int code;
    private String msg;

    ResultEnum(int code, String msg){
        this.code = code;
        this.msg = msg;
    }
}
