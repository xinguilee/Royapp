package com.demo.smart.service.impl;

import com.demo.smart.config.UsernameIsExitedException;
import com.demo.smart.mapper.UserMapper;
import com.demo.smart.model.User;
import com.demo.smart.security.JwtUtils;
import com.demo.smart.service.UserService;
import com.demo.smart.utils.SnowflakeIdWorker;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Description: smart
 * Created By pjf on 2019/1/5
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private UserDetailsService userDetailsService;

    @Override
    public List<User> getAllUser() {
        return userMapper.selectAllUser();
    }

    //登录
    @Override
    public String login(User user) throws Exception {
        //获取用户名&密码
        String username = user.getUsername();
        String password = user.getPassword();

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        if(!new BCryptPasswordEncoder().matches(password, userDetails.getPassword())){
            throw new UsernameIsExitedException("密码错误");
        }

        final Authentication auth = new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(auth);

        String token = JwtUtils.Instance().createToken(auth);

        return token;
    }

    //注册
    @Override
    public User register(User user) throws Exception {
        //TODO
        //获取ID
        Long id = SnowflakeIdWorker.Instance().nextId();
        //密码加密
        String password = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setId(id);
        user.setPassword(password);
        user.setSalt("");

        userMapper.insertUser(user);

        return user;
    }
}
