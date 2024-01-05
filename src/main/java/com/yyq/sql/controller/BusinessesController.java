package com.yyq.sql.controller;


import com.yyq.sql.common.BaseResponse;
import com.yyq.sql.common.ErrorCode;
import com.yyq.sql.common.PageRequest;
import com.yyq.sql.common.ResultUtils;
import com.yyq.sql.exception.BusinessException;
import com.yyq.sql.model.domain.Businesses;
import com.yyq.sql.model.domain.Foods;
import com.yyq.sql.model.dto.business.BizQueryOrderRequest;
import com.yyq.sql.model.dto.business.BusinessFoodOrderDTO;
import com.yyq.sql.model.dto.business.BusinessLoginRequest;
import com.yyq.sql.model.dto.rider.RiderLoginRequest;
import com.yyq.sql.model.vo.business.BusinessVO;
import com.yyq.sql.model.vo.business.ListBusinessesVO;
import com.yyq.sql.model.vo.order.ListOrdersVO;
import com.yyq.sql.model.vo.order.OrdersVO;
import com.yyq.sql.service.AddressService;
import com.yyq.sql.service.BusinessesService;
import com.yyq.sql.service.OrdersService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import java.util.List;

import static com.yyq.sql.constant.UserConstant.BUSINESS_LOGIN_STATE;


/**
 * @description:
 * @author: Yiqi Yu
 * @time: 2023/11/24
 */
@RestController
@RequestMapping("/business")
@CrossOrigin
public class BusinessesController {

    @Resource
    private BusinessesService businessesService;

    @Resource
    private OrdersService ordersService;

    @PostMapping("/login")
    public BaseResponse<Integer> login(@RequestBody BusinessLoginRequest businessLoginRequest, HttpServletRequest request) {
        Integer result = businessesService.login(businessLoginRequest, request);
        return ResultUtils.success(result);
    }

    /**
     * 获取当前登录用户
     *
     * @param request
     * @return
     */
    @GetMapping("/get/login")
    public BaseResponse<BusinessVO> getLoginUser(HttpServletRequest request) {
        Businesses businesses = (Businesses) request.getSession().getAttribute(BUSINESS_LOGIN_STATE);
        if(businesses == null) {
            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR);
        }
        BusinessVO businessVO = new BusinessVO();
        BeanUtils.copyProperties(businesses, businessVO);
        return ResultUtils.success(businessVO);
    }

    /**
     * 订单
     * @param bizQueryOrderRequest
     * @return
     */
    @GetMapping("/listMy")
    public BaseResponse<ListOrdersVO> listBizMyOrder(BizQueryOrderRequest bizQueryOrderRequest) {
        ListOrdersVO listOrdersVO = ordersService.listBizMy(bizQueryOrderRequest);

        return ResultUtils.success(listOrdersVO);
    }

    @GetMapping("/get")
    public BaseResponse<BusinessVO> getBizById(long id) {
        Businesses businesses = businessesService.getBizById(id);
        BusinessVO businessVO = new BusinessVO();
        BeanUtils.copyProperties(businesses, businessVO);
        return ResultUtils.success(businessVO);
    }

    @GetMapping("/list")
    public BaseResponse<ListBusinessesVO> listBiz(PageRequest pageRequest) {
        ListBusinessesVO listBusinessesVO = businessesService.listBiz(pageRequest);

        return ResultUtils.success(listBusinessesVO);
    }

    @GetMapping("/listBusinessFoodOrder")
    public BaseResponse<List<BusinessFoodOrderDTO>> listBusinessFoodOrder() {
        List<BusinessFoodOrderDTO> listBusinessFoodOrder = businessesService.listBusinessFoodOrder();
        return ResultUtils.success(listBusinessFoodOrder);
    }




}

