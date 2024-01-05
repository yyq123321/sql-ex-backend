package com.yyq.sql.service;

import com.yyq.sql.common.PageRequest;
import com.yyq.sql.model.domain.Foods;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yyq.sql.model.dto.food.FoodAddRequest;
import com.yyq.sql.model.dto.food.FoodOrderSummaryDTO;
import com.yyq.sql.model.dto.food.FoodsQueryRequest;
import com.yyq.sql.model.vo.food.ListFoodsVO;

import java.util.List;

/**
* @author Acer
* @description 针对表【foods】的数据库操作Service
* @createDate 2023-11-24 15:30:03
*/
public interface FoodsService extends IService<Foods> {

    Integer add(FoodAddRequest foodAddRequest);

    ListFoodsVO list(FoodsQueryRequest foodsQueryRequest);

    Foods getFoodsById(long id);

    List<FoodOrderSummaryDTO> listFoodOrder();
}
