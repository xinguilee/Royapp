package com.demo.smart.mapper;

import com.demo.smart.model.User;

import java.util.List;

/**
 * Description: smart
 * Created By pjf on 2019/1/1
 */
public interface UserMapper {
    List<User> selectAllUser();

    User selectByUsername(String username);

    int insertUser(User user);
}
