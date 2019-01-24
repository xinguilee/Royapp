package com.demo.smart.utils;

public class ResultHandler {

    /**
     * 成功
     * @param o
     * @return
     */
    public static ResultBean ok(Object o){

        return new ResultBean(o);
    }

    /**
     * 异常
     * @param e
     * @return
     */
    public static ResultBean error(Throwable e){
        return new ResultBean(e);
    }

    /**
     * 自定义
     * @param code
     * @param msg
     * @param data
     * @return
     */
    public static ResultBean request(int code, String msg, Object data){
        return new ResultBean(code, msg, data);
    }
}
