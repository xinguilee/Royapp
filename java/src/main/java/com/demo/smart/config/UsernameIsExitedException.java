package com.demo.smart.config;


import org.springframework.security.core.AuthenticationException;

/**
 * Description: smart
 * Created By pjf on 2019/1/6
 */
public class UsernameIsExitedException extends AuthenticationException {
    public UsernameIsExitedException(String msg){
        super(msg);
    }

    public UsernameIsExitedException(String msg, Throwable t){
        super(msg, t);
    }
}
