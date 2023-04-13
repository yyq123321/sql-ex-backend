package com.yyq.usercenter.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.yyq.usercenter.common.BaseResponse;
import com.yyq.usercenter.common.ErrorCode;
import com.yyq.usercenter.common.ResultUtils;
import com.yyq.usercenter.constant.UserConstant;
import com.yyq.usercenter.exception.BusinessException;
import com.yyq.usercenter.model.domain.User;
import com.yyq.usercenter.model.domain.request.UserLoginRequest;
import com.yyq.usercenter.model.domain.request.UserRegisterRequest;
import com.yyq.usercenter.service.UserService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.yyq.usercenter.constant.UserConstant.ADMIN_ROLE;
import static com.yyq.usercenter.constant.UserConstant.USER_LOGIN_STATE;

/**
 * @description:
 * @author: Yiqi Yu
 * @time: 2023/4/6 21:32
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 注册
     * @param userRegisterRequest
     * @return
     */
    @PostMapping("/register")
    public BaseResponse<Long> userRegister(@RequestBody UserRegisterRequest userRegisterRequest) {
        if (userRegisterRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "信息为空");
        }
        String userPassword = userRegisterRequest.getUserPassword();
        String userAccount = userRegisterRequest.getUserAccount();
        String checkPassword = userRegisterRequest.getCheckPassword();
        String planetCode = userRegisterRequest.getPlanetCode();
        if(StringUtils.isAnyBlank(userAccount, userPassword, checkPassword, planetCode)) {
            throw new BusinessException(ErrorCode.NULL_ERROR,"字段不能为空");
        }
        long result = userService.userRegister(userAccount, userPassword, checkPassword, planetCode);
        return ResultUtils.success(result);
    }

    /**
     * 用户登录
     * @param userLoginRequest
     * @param request
     * @return
     */
    @PostMapping("/login")
    public BaseResponse<User> userLogin(@RequestBody UserLoginRequest userLoginRequest, HttpServletRequest request) {
        if (userLoginRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        String userPassword = userLoginRequest.getUserPassword();
        String userAccount = userLoginRequest.getUserAccount();
        System.out.println(userAccount + userPassword);
        if(StringUtils.isAnyBlank(userAccount, userPassword)) {
            return ResultUtils.error(ErrorCode.NULL_ERROR);
        }
        User user = userService.userLogin(userAccount, userPassword, request);
        return ResultUtils.success(user);
    }

    /**
     * 用户注销
     * @param request
     * @return
     */
    @PostMapping("/logout")
    public BaseResponse<Integer> userLogout(HttpServletRequest request) {
        if (request == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        int result = userService.userLogout(request);
        return ResultUtils.success(result);
    }
    /**
     * 搜索用户
     * @param username
     * @return
     */
    @GetMapping("/search")
    public BaseResponse<List<User>> searchUserList(String username, HttpServletRequest request) {
        if(!isAdmin(request)) {
            throw new BusinessException(ErrorCode.NO_AUTH);
        }

        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        if(StringUtils.isNotBlank(username)) {
            queryWrapper.like(User::getUsername, username);
        }
        List<User> userList = userService.list(queryWrapper);
        userList = userList.stream().map(user -> userService.getSafetyUser(user)).collect(Collectors.toList());
        return ResultUtils.success(userList);
    }

    /**
     * 逻辑删除
     * @param id
     * @return
     */
    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteUser(@RequestBody Long id, HttpServletRequest request) {
        if(!isAdmin(request)) {
            throw new BusinessException(ErrorCode.NO_AUTH);
        }
        if(id <= 0) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR);
        }
        boolean result = userService.removeById(id);
        return ResultUtils.success(result);
    }

    @GetMapping("/current")
    public BaseResponse<User> getCurrentUser(HttpServletRequest request) {
        User currentUser = (User) request.getSession().getAttribute(USER_LOGIN_STATE);
        if(currentUser == null) {
            throw new BusinessException(ErrorCode.NOT_LOGIN);
        }
        Long id = currentUser.getId();
        // todo 校验用户是否合法
        User user = userService.getById(id);
        User safetyUser = userService.getSafetyUser(user);
        return ResultUtils.success(safetyUser);
    }

    private Boolean isAdmin(HttpServletRequest request) {
        //仅管理员可以删除
        User user = (User) request.getSession().getAttribute(USER_LOGIN_STATE);
        if(user == null || user.getUserRole() != ADMIN_ROLE) {
            return false;
        }
        return true;
    }


}

