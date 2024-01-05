package com.yyq.sql.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yyq.sql.model.domain.Customers;
import com.yyq.sql.model.domain.Riders;
import com.yyq.sql.model.dto.rider.RiderLoginRequest;
import com.yyq.sql.model.dto.rider.RiderRegisterRequest;
import com.yyq.sql.service.RidersService;
import com.yyq.sql.mapper.RidersMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import static com.yyq.sql.constant.UserConstant.CUSTOMER_LOGIN_STATE;
import static com.yyq.sql.constant.UserConstant.RIDER_LOGIN_STATE;

/**
* @author Acer
* @description 针对表【riders】的数据库操作Service实现
* @createDate 2023-11-24 15:30:03
*/
@Service
public class RidersServiceImpl extends ServiceImpl<RidersMapper, Riders>
implements RidersService{

    @Resource
    private RidersMapper ridersMapper;

    @Override
    public Integer login(RiderLoginRequest riderLoginRequest, HttpServletRequest request) {
        Riders riders = ridersMapper.login(riderLoginRequest);
        if(riders == null) {
            return 0;
        }
        riders.setPassword(null);
        //记录用户的登录态
        request.getSession().setAttribute(RIDER_LOGIN_STATE, riders);
        return 1;
    }

    @Override
    public Integer register(RiderRegisterRequest registerRequest) {
        Riders riders = new Riders();
        BeanUtils.copyProperties(registerRequest, riders);
        return ridersMapper.register(riders);
    }

    @Override
    public Riders getRidersById(long id) {
        return ridersMapper.getById(id);
    }
}
