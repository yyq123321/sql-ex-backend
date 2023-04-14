package com.yyq.usercenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yyq.usercenter.common.ErrorCode;
import com.yyq.usercenter.common.ResultUtils;
import com.yyq.usercenter.exception.BusinessException;
import com.yyq.usercenter.model.domain.User;
import com.yyq.usercenter.service.UserService;
import com.yyq.usercenter.mapper.UserMapper;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.yyq.usercenter.constant.UserConstant.USER_LOGIN_STATE;

/**
* @author Acer
* @description 针对表【originUser(用户)】的数据库操作Service实现
* @createDate 2023-04-05 11:19:35
*/
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

    @Resource
    private UserMapper userMapper;

    /**
     * 盐值混淆密码
     */
    private static final String SALT = "hanyi";

    @Override
    public long userRegister(String userAccount, String userPassword, String checkPassword, String planetCode) {
        //校验
        if(StringUtils.isAnyBlank(userAccount, userPassword, checkPassword, planetCode)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数为空");
        }
        //校验账户
        if(userAccount.length() < 4) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"账号过短");
        }

        if(userPassword.length() < 6 || checkPassword.length() < 6) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"密码少于六位");
        }

        String regEx = "^[a-zA-Z0-9_-]{4,16}$"; //要求账户名只包含字母、数字、下划线和减号，长度在4到16位之间
        Matcher matcher = Pattern.compile(regEx).matcher(userAccount);
        if(!matcher.matches()) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "用户名不合法");
        }

        if(!userPassword.equals(checkPassword)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "密码不一致");
        }

        LambdaQueryWrapper<User> queryMapper = new LambdaQueryWrapper<>();
        queryMapper.eq(User::getUserAccount, userAccount);
        long count = this.count(queryMapper);
        if(count > 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "用户已存在");
        }

        //编号不能重复
        queryMapper = new LambdaQueryWrapper<>();
        queryMapper.eq(User::getPlanetCode, planetCode);
        count = this.count(queryMapper);
        if(count > 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "唯一编号已经存在");
        }

        //加密
        String password = DigestUtils.md5DigestAsHex((SALT + userPassword).getBytes());

        //插入数据
        User user = new User();
        user.setUserAccount(userAccount);
        user.setUserPassword(password);
        user.setPlanetCode(planetCode);
        boolean saveResult = this.save(user);
        if(!saveResult) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "保存数据出现错误");
        }
        return user.getId();
    }

    @Override
    public User userLogin(String userAccount, String userPassword, HttpServletRequest request) {
        //校验
        if(StringUtils.isAnyBlank(userAccount, userPassword)) {
            throw new BusinessException(ErrorCode.NULL_ERROR, "请求数据为空");
        }
        //校验账户
        if(userAccount.length() < 4) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "账户小于四位");
        }

        if(userPassword.length() < 6) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "密码小于六位");
        }

        String regEx = "^[a-zA-Z0-9_-]{4,16}$"; //要求账户名只包含字母、数字、下划线和减号，长度在4到16位之间
        Matcher matcher = Pattern.compile(regEx).matcher(userAccount);
        if(!matcher.matches()) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "账户名不符合规则");
        }

        //加密
        userPassword = DigestUtils.md5DigestAsHex((SALT + userPassword).getBytes());

        LambdaQueryWrapper<User> queryMapper = new LambdaQueryWrapper<>();
        queryMapper.eq(User::getUserAccount, userAccount);
        queryMapper.eq(User::getUserPassword, userPassword);
        User user = userMapper.selectOne(queryMapper);
        if(user == null) {
            log.info("originUser login failed, userAccount cannot match userPassword");
            throw new BusinessException(ErrorCode.NULL_ERROR, "用户不存在");
        }

        //用户脱敏
        User safetyUser = getSafetyUser(user);

        //记录用户的登录态
        request.getSession().setAttribute(USER_LOGIN_STATE, safetyUser);

        return safetyUser;
    }

    /**
     * 用户脱敏
     * @param originUser
     * @return
     */
    @Override
    public User getSafetyUser(User originUser) {
        if(originUser == null) {
            return null;
        }
        User safetyUser = new User();
        safetyUser.setId(originUser.getId());
        safetyUser.setUsername(originUser.getUsername());
        safetyUser.setUserAccount(originUser.getUserAccount());
        safetyUser.setGender(originUser.getGender());
        safetyUser.setAvatarUrl(originUser.getAvatarUrl());
        safetyUser.setPhone(originUser.getPhone());
        safetyUser.setEmail(originUser.getEmail());
        safetyUser.setUserRole(originUser.getUserRole());
        safetyUser.setPlanetCode(originUser.getPlanetCode());
        safetyUser.setUserStatus(originUser.getUserStatus());
        safetyUser.setCreateTime(originUser.getCreateTime());
        return safetyUser;
    }

    /**
     * 用户注销
     *
     * @param request
     */
    @Override
    public int userLogout(HttpServletRequest request) {
        request.getSession().removeAttribute(USER_LOGIN_STATE);
        return 1;
    }
}




