package com.yyq.usercenter.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yyq.usercenter.model.domain.User;

import javax.servlet.http.HttpServletRequest;

/**
* @author Acer
* @description 针对表【user(用户)】的数据库操作Service
* @createDate 2023-04-05 11:19:35
*/
public interface UserService extends IService<User> {



    /**
     * 用户注册
     *
     * @param userAccount
     * @param userPassword
     * @param checkPassword
     * @param planetCode
     * @return
     */
    long userRegister(String userAccount, String userPassword, String checkPassword, String planetCode);

    /**
     * 用户登录
     * @param userAccount
     * @param userPassword
     * @return
     */
    User userLogin(String userAccount, String userPassword, HttpServletRequest request);

    /**
     * 用户脱敏
     * @param originUser
     * @return
     */
    User getSafetyUser(User originUser);

    /**
     * 注销
     *
     * @param request
     */
    int userLogout(HttpServletRequest request);
}
