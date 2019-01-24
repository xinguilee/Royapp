package com.demo.smart.service.impl;

import com.demo.smart.mapper.RoleMapper;
import com.demo.smart.mapper.UserMapper;
import com.demo.smart.model.JwtUserDetails;
import com.demo.smart.model.Role;
import com.demo.smart.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private RoleMapper roleMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.selectByUsername(username);

        if(user == null){
            throw new UsernameNotFoundException("用户信息不存在");
        }

        List<Role> roles = roleMapper.selectByUserId(user.getId());

        return new JwtUserDetails(user.getId(), user.getUsername(), user.getPassword(), user.getSalt(), roles, new Date());
    }
}
