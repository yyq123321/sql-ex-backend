package com.yyq.sql.controller;


import com.yyq.sql.common.BaseResponse;
import com.yyq.sql.common.ErrorCode;
import com.yyq.sql.common.ResultUtils;
import com.yyq.sql.exception.BusinessException;
import com.yyq.sql.model.domain.Businesses;
import com.yyq.sql.model.domain.Customers;
import com.yyq.sql.model.domain.Riders;
import com.yyq.sql.model.dto.address.AddressAddRequest;
import com.yyq.sql.model.dto.customer.CusQueryOrderRequest;
import com.yyq.sql.model.dto.rider.RiderLoginRequest;
import com.yyq.sql.model.dto.rider.RiderQueryOrderRequest;
import com.yyq.sql.model.dto.rider.RiderRegisterRequest;
import com.yyq.sql.model.vo.business.BusinessVO;
import com.yyq.sql.model.vo.customer.CustomerVO;
import com.yyq.sql.model.vo.order.ListOrdersVO;
import com.yyq.sql.model.vo.order.OrdersVO;
import com.yyq.sql.model.vo.rider.RiderVO;
import com.yyq.sql.service.AddressService;
import com.yyq.sql.service.OrdersService;
import com.yyq.sql.service.RidersService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import java.util.List;

import static com.yyq.sql.constant.UserConstant.CUSTOMER_LOGIN_STATE;
import static com.yyq.sql.constant.UserConstant.RIDER_LOGIN_STATE;


/**
 * @description:
 * @author: Yiqi Yu
 * @time: 2023/11/24
 */
@RestController
@RequestMapping("/rider")
@CrossOrigin
public class RidersController {

    @Resource
    private RidersService ridersService;

    @Resource
    private OrdersService ordersService;

    @PostMapping("/login")
    public BaseResponse<Integer> login(@RequestBody RiderLoginRequest riderLoginRequest,
                                       HttpServletRequest request) {
        Integer result = ridersService.login(riderLoginRequest, request);
        return ResultUtils.success(result);
    }

    /**
     * 获取当前登录用户
     *
     * @param request
     * @return
     */
    @GetMapping("/get/login")
    public BaseResponse<RiderVO> getLoginUser(HttpServletRequest request) {
        Riders riders = (Riders) request.getSession().getAttribute(RIDER_LOGIN_STATE);
        if(riders == null) {
            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR);
        }
        RiderVO riderVO = new RiderVO();
        BeanUtils.copyProperties(riders, riderVO);
        return ResultUtils.success(riderVO);
    }

    @GetMapping("/listMy")
    public BaseResponse<ListOrdersVO> listRiderMyOrder(RiderQueryOrderRequest riderQueryOrderRequest) {
        ListOrdersVO ordersVOList = ordersService.listRiderMy(riderQueryOrderRequest);
        return ResultUtils.success(ordersVOList);
    }

    @GetMapping("/get")
    public BaseResponse<RiderVO> getRiderById(long id) {
        Riders riders = ridersService.getRidersById(id);
        RiderVO riderVO = new RiderVO();
        BeanUtils.copyProperties(riders, riderVO);
        return ResultUtils.success(riderVO);
    }



}

