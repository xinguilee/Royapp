package com.demo.smart.controller;

import com.demo.smart.model.User;
import com.demo.smart.service.UserService;
import com.demo.smart.utils.ResultBean;
import com.demo.smart.utils.ResultHandler;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

/**
 * Description: smart
 * Created By pjf on 2019/1/5
 */
@RestController
public class UserController {

    @Resource
    private UserService userService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(value = "/all")
    public ResultBean getAllUser(){
        List<User> users = userService.getAllUser();

        return ResultHandler.ok(users);
    }

    @PostMapping(value = "/login")
    public ResultBean login(HttpServletResponse response, @Valid User user) throws Exception{
        String token = userService.login(user);

        response.addHeader("token", token);

        return ResultHandler.ok(null);
    }

    @PostMapping(value = "/register")
    public ResultBean register(@Valid User user) throws Exception{
        userService.register(user);

        return ResultHandler.ok(user);
    }
}
