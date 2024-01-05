package com.yyq.sql.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yyq.sql.model.domain.Customers;
import com.yyq.sql.model.dto.customer.CustomerLoginRequest;

import javax.servlet.http.HttpServletRequest;

/**
* @author Acer
* @description 针对表【customers】的数据库操作Service
* @createDate 2023-11-24 15:30:03
*/
public interface CustomersService extends IService<Customers> {

    Integer login(CustomerLoginRequest customerLoginRequest, HttpServletRequest request);

    Customers getCusById(long id);
}
