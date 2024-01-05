package com.yyq.sql.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yyq.sql.model.domain.Businesses;
import com.yyq.sql.model.domain.Customers;
import com.yyq.sql.model.dto.customer.CustomerLoginRequest;
import com.yyq.sql.service.CustomersService;
import com.yyq.sql.mapper.CustomersMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import static com.yyq.sql.constant.UserConstant.BUSINESS_LOGIN_STATE;
import static com.yyq.sql.constant.UserConstant.CUSTOMER_LOGIN_STATE;

/**
* @author Acer
* @description 针对表【customers】的数据库操作Service实现
* @createDate 2023-11-24 15:30:03
*/
@Service
public class CustomersServiceImpl extends ServiceImpl<CustomersMapper, Customers>
implements CustomersService{

    @Resource
    private CustomersMapper customersMapper;
    @Override
    public Integer login(CustomerLoginRequest customerLoginRequest, HttpServletRequest request) {
        Customers customers = customersMapper.login(customerLoginRequest);
        if(customers == null) {
            return 0;
        }
        customers.setPassword(null);
        //记录用户的登录态
        request.getSession().setAttribute(CUSTOMER_LOGIN_STATE, customers);
        return 1;
    }

    @Override
    public Customers getCusById(long id) {
        return customersMapper.getById(id);
    }
}
