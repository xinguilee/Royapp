package com.demo.smart.config;

import com.demo.smart.utils.ResultBean;
import com.demo.smart.utils.ResultHandler;
import com.google.gson.GsonBuilder;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*@Component*/
public class SecurityExceptionHandle implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        httpServletResponse.setHeader("Content-Type", "application/json;charset=utf-8");
        ResultBean resultBean = ResultHandler.request(401, "权限不足", null);
        httpServletResponse.getWriter().write(new GsonBuilder().create().toJson(resultBean));
        httpServletResponse.getWriter().flush();
    }
}
