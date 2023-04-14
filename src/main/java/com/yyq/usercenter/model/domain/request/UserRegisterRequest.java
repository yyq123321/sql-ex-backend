package com.yyq.usercenter.model.domain.request;

import lombok.Data;

import java.io.Serializable;

/**
 * @description: 用户注册请求体
 * @author: Yiqi Yu
 * @time: 2023/4/6 21:38
 */
@Data
public class UserRegisterRequest implements Serializable {

    private static final long serialVersionUID = 8788052645952736297L;

    private String userAccount;
    private String userPassword;
    private String checkPassword;
    private String planetCode;
}
