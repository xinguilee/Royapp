package com.demo.smart.service;

import com.demo.smart.model.User;

import java.util.List;

/**
 * Description: smart
 * Created By pjf on 2019/1/5
 */
public interface UserService {
    List<User> getAllUser();

    /**
     * 登录
     * @param user
     * @return
     * @throws Exception
     */
    String login(User user) throws Exception;

    /**
     * 注册
     * @param user
     * @return
     * @throws Exception
     */
    User register(User user) throws Exception;
}
