package com.yyq.sql.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yyq.sql.common.PageRequest;
import com.yyq.sql.model.domain.Foods;
import com.yyq.sql.model.dto.food.FoodAddRequest;
import com.yyq.sql.model.dto.food.FoodOrderSummaryDTO;
import com.yyq.sql.model.dto.food.FoodsQueryRequest;
import com.yyq.sql.model.vo.food.FoodsVO;
import com.yyq.sql.model.vo.food.ListFoodsVO;
import com.yyq.sql.service.FoodsService;
import com.yyq.sql.mapper.FoodsMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
* @author Acer
* @description 针对表【foods】的数据库操作Service实现
* @createDate 2023-11-24 15:30:03
*/
@Service
public class FoodsServiceImpl extends ServiceImpl<FoodsMapper, Foods>
implements FoodsService{

    @Resource
    private FoodsMapper foodsMapper;

    @Override
    public Integer add(FoodAddRequest foodAddRequest) {
        return foodsMapper.addFood(foodAddRequest);
    }

    @Override
    public ListFoodsVO list(FoodsQueryRequest foodsQueryRequest) {
        Long businessId = foodsQueryRequest.getBusinessId();
        long current = foodsQueryRequest.getCurrent();
        long pageSize = foodsQueryRequest.getPageSize();
        List<Foods> foods = foodsMapper.listByPage(businessId, (current - 1) * pageSize,
                pageSize);
        Long countFoods = foodsMapper.countFoods(foodsQueryRequest.getBusinessId());
        return new ListFoodsVO(foods, countFoods);
    }

    @Override
    public Foods getFoodsById(long id) {
        return foodsMapper.getById(id);
    }

    @Override
    public List<FoodOrderSummaryDTO> listFoodOrder() {
        return foodsMapper.listFoodOrder();
    }
}
