package com.demo.smart.utils;

import lombok.Data;

import java.io.Serializable;

@Data
public class ResultBean<T> implements Serializable {
    private static final int SUCCESS = ResultEnum.SUCCESS.getCode(); //成功

    private static final int FAIL = ResultEnum.FAIL.getCode(); //失败

    private String msg = ResultEnum.SUCCESS.getMsg();

    private int code = SUCCESS;

    private T data;

    public ResultBean() {
        super();
    }

    public ResultBean(T data) {
        super();
        this.data = data;
    }

    public ResultBean(Throwable e) {
        super();
        this.msg = e.getMessage();
        this.code = FAIL;
    }

    public ResultBean(int code, String msg, T data){
        super();
        this.data = data;
        this.code = code;
        this.msg = msg;
    }
}
