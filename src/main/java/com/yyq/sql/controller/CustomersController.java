package com.yyq.sql.controller;


import com.yyq.sql.common.BaseResponse;
import com.yyq.sql.common.ErrorCode;
import com.yyq.sql.common.ResultUtils;
import com.yyq.sql.exception.BusinessException;
import com.yyq.sql.model.domain.Businesses;
import com.yyq.sql.model.domain.Customers;
import com.yyq.sql.model.dto.business.BizQueryOrderRequest;
import com.yyq.sql.model.dto.customer.CusQueryOrderRequest;
import com.yyq.sql.model.dto.customer.CustomerLoginRequest;
import com.yyq.sql.model.dto.rider.RiderLoginRequest;
import com.yyq.sql.model.vo.business.BusinessVO;
import com.yyq.sql.model.vo.customer.CustomerVO;
import com.yyq.sql.model.vo.order.ListOrdersVO;
import com.yyq.sql.model.vo.order.OrdersVO;
import com.yyq.sql.service.AddressService;
import com.yyq.sql.service.CustomersService;
import com.yyq.sql.service.OrdersService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import java.util.List;

import static com.yyq.sql.constant.UserConstant.BUSINESS_LOGIN_STATE;
import static com.yyq.sql.constant.UserConstant.CUSTOMER_LOGIN_STATE;


/**
 * @description:
 * @author: Yiqi Yu
 * @time: 2023/11/24
 */
@RestController
@RequestMapping("/customer")
@CrossOrigin
public class CustomersController {

    @Resource
    private CustomersService customersService;

    @Resource
    private OrdersService ordersService;

    @PostMapping("/login")
    public BaseResponse<Integer> login(@RequestBody CustomerLoginRequest customerLoginRequest,
                                       HttpServletRequest request) {
        Integer result = customersService.login(customerLoginRequest, request);
        return ResultUtils.success(result);
    }

    /**
     * 获取当前登录用户
     *
     * @param request
     * @return
     */
    @GetMapping("/get/login")
    public BaseResponse<CustomerVO> getLoginUser(HttpServletRequest request) {
        Customers customers = (Customers) request.getSession().getAttribute(CUSTOMER_LOGIN_STATE);
        if(customers == null) {
            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR);
        }
        CustomerVO customerVO = new CustomerVO();
        BeanUtils.copyProperties(customers, customerVO);
        return ResultUtils.success(customerVO);
    }

    @GetMapping("/listMy")
    public BaseResponse<ListOrdersVO> listCusMyOrder(CusQueryOrderRequest cusQueryOrderRequest) {
        ListOrdersVO ordersVOList = ordersService.listCusMy(cusQueryOrderRequest);
        return ResultUtils.success(ordersVOList);
    }

    @GetMapping("/get")
    public BaseResponse<CustomerVO> getCusById(long id) {
        Customers customers = customersService.getCusById(id);
        CustomerVO customerVO = new CustomerVO();
        BeanUtils.copyProperties(customers, customerVO);
        return ResultUtils.success(customerVO);
    }

}

