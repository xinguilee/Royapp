package com.demo.smart.model;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class User {
    private Long id;

    @NotNull(message = "用户名不能为空")
    private String username;

    @NotNull(message = "密码不能为空")
    private String password;

    private String salt;

    private String creator;

    private Date createtime;

    private String lastmodifycreator;

    private Date lastmodifytime;
}
