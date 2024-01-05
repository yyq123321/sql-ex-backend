package com.yyq.sql.controller;


import com.yyq.sql.common.BaseResponse;
import com.yyq.sql.common.PageRequest;
import com.yyq.sql.common.ResultUtils;
import com.yyq.sql.model.domain.Foods;
import com.yyq.sql.model.domain.Orders;
import com.yyq.sql.model.dto.food.FoodsUpdateRequest;
import com.yyq.sql.model.dto.order.OrderCreateRequest;
import com.yyq.sql.model.dto.order.OrderSummaryDTO;
import com.yyq.sql.model.dto.order.OrdersUpdateRequest;
import com.yyq.sql.model.vo.order.ListOrdersVO;
import com.yyq.sql.model.vo.order.OrdersVO;
import com.yyq.sql.service.OrdersService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


/**
 * @description:
 * @author: Yiqi Yu
 * @time: 2023/11/24
 */
@RestController
@RequestMapping("/order")
@CrossOrigin
public class OrdersController {

    @Resource
    private OrdersService ordersService;

    @PostMapping("/create")
    public BaseResponse<Integer> createOrder(@RequestBody OrderCreateRequest orderCreateRequest) {
        Integer result = ordersService.create(orderCreateRequest);
        return ResultUtils.success(result);
    }

    @GetMapping("/list")
    public BaseResponse<ListOrdersVO> listOrder(PageRequest ordersQueryRequest) {
        ListOrdersVO ordersVOList = ordersService.list(ordersQueryRequest);
        return ResultUtils.success(ordersVOList);
    }

    @GetMapping("/listNoRider")
    public BaseResponse<ListOrdersVO> listNoRiderOrder(PageRequest ordersQueryRequest) {
        ListOrdersVO ordersVOList = ordersService.listNoRiderOrder(ordersQueryRequest);
        return ResultUtils.success(ordersVOList);
    }

    @PostMapping("/update")
    public BaseResponse<Integer> updateOrder(@RequestBody OrdersUpdateRequest ordersUpdateRequest) {
        Integer result = ordersService.updateOrder(ordersUpdateRequest);
        return ResultUtils.success(result);
    }

    @GetMapping("/get")
    public BaseResponse<OrdersVO> getOrderById(long id) {
        OrdersVO ordersVO = ordersService.getOrdersById(id);

        return ResultUtils.success(ordersVO);
    }

    @GetMapping("/listOrderSummary")
    public BaseResponse<List<OrderSummaryDTO>> listOrderSummary() {
        List<OrderSummaryDTO> result = ordersService.listOrderSummary();

        return ResultUtils.success(result);
    }







}

