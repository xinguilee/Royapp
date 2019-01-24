package com.demo.smart.mapper;

import com.demo.smart.model.Role;

import java.util.List;

/**
 * Description: smart
 * Created By pjf on 2019/1/1
 */
public interface RoleMapper {
    /**
     * 查询用户所拥有角色
     * @param userId
     * @return
     */
    List<Role> selectByUserId(Long userId);
}
