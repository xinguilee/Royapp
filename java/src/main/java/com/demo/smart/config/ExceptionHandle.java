package com.demo.smart.config;

import com.demo.smart.utils.ResultBean;
import com.demo.smart.utils.ResultHandler;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 异常捕获类
 */
@RestControllerAdvice
public class ExceptionHandle {

    @ExceptionHandler(value = Exception.class)
    public ResultBean handle(Exception e){

        return ResultHandler.error(e);
    }

    @ExceptionHandler(value = BindException.class)
    public ResultBean validHandler(BindException e){
        BindingResult bindingResult = e.getBindingResult();
        StringBuilder sb = new StringBuilder();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            sb.append(fieldError.getDefaultMessage() + ",");
        }
        if(sb.length() > 0){
            sb.setLength(sb.length() - 1);
        }

        return ResultHandler.request(500, sb.toString(), "");
    }
}
