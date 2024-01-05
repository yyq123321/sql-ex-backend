package com.yyq.sql.controller;


import com.yyq.sql.common.BaseResponse;
import com.yyq.sql.common.PageRequest;
import com.yyq.sql.common.ResultUtils;
import com.yyq.sql.model.domain.Address;
import com.yyq.sql.model.domain.Foods;
import com.yyq.sql.model.dto.address.AddressUpdateRequest;
import com.yyq.sql.model.dto.food.FoodAddRequest;
import com.yyq.sql.model.dto.food.FoodOrderSummaryDTO;
import com.yyq.sql.model.dto.food.FoodsQueryRequest;
import com.yyq.sql.model.dto.food.FoodsUpdateRequest;
import com.yyq.sql.model.vo.food.FoodsVO;
import com.yyq.sql.model.vo.food.ListFoodsVO;
import com.yyq.sql.service.FoodsService;
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
@RequestMapping("/food")
@CrossOrigin
public class FoodsController {

    @Resource
    private FoodsService foodsService;

    @PostMapping("/add")
    public BaseResponse<Integer> addFoods(@RequestBody FoodAddRequest foodAddRequest) {
        Integer result = foodsService.add(foodAddRequest);
        return ResultUtils.success(result);
    }

    @GetMapping("/list")
    public BaseResponse<ListFoodsVO> listFoods(FoodsQueryRequest foodsQueryRequest) {
        ListFoodsVO listFoodsVO = foodsService.list(foodsQueryRequest);
        return ResultUtils.success(listFoodsVO);
    }

    @PostMapping("/update")
    public BaseResponse<Integer> updateFoods(@RequestBody FoodsUpdateRequest foodsUpdateRequest) {
        Foods foods = new Foods();
        BeanUtils.copyProperties(foodsUpdateRequest, foods);
        boolean result = foodsService.updateById(foods);
        return ResultUtils.success(result ? 1 : 0);
    }

    @GetMapping("/get")
    public BaseResponse<Foods> getFoodsById(long id) {
        Foods result = foodsService.getFoodsById(id);
        return ResultUtils.success(result);
    }


    @GetMapping("/listFoodOrder")
    public BaseResponse<List<FoodOrderSummaryDTO>> listFoodOrder() {
        List<FoodOrderSummaryDTO> result = foodsService.listFoodOrder();
        return ResultUtils.success(result);
    }



}

