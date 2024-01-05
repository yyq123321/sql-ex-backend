package com.yyq.sql.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yyq.sql.common.PageRequest;
import com.yyq.sql.model.domain.Address;
import com.yyq.sql.model.domain.Foods;
import com.yyq.sql.model.dto.food.FoodAddRequest;
import com.yyq.sql.model.dto.food.FoodOrderSummaryDTO;
import com.yyq.sql.model.dto.food.FoodsQueryRequest;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
* @author Acer
* @description 针对表【foods】的数据库操作Mapper
* @createDate 2023-11-24 15:30:03
* @Entity com.yyq.sql.model.domain.Foods
*/

public interface FoodsMapper extends BaseMapper<Foods> {

    @Select("SELECT *, business_id AS businessId FROM foods WHERE id = #{id};")
    Foods getById(@Param("id") Long id);

    @Insert("INSERT INTO foods (name, category, business_id, price, photo) " +
            "VALUES (#{name}, #{category}, #{businessId}, #{price}, #{photo})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    Integer addFood(FoodAddRequest foodAddRequest);

    @Update("UPDATE foods SET name = #{name}, category = #{category}, " +
            "business_id = #{businessId}, price = #{price}, photo = #{photo} WHERE id = #{id}")
    void updateFood(Foods food);

    @Select("SELECT * FROM foods " +
            "WHERE business_id = #{businessId} " +
            "ORDER BY update_time " +
            "LIMIT #{offset}, #{pageSize};")
    List<Foods> listByPage(@Param("businessId") Long businessId, @Param("offset") Long offset,
                           @Param("pageSize") Long pageSize);

    @Select("SELECT COUNT(*) FROM foods " +
            "WHERE business_id = #{businessId};")
    Long countFoods(Long businessId);

    @Delete("DELETE FROM foods WHERE id = #{id}")
    void deleteFoodById(@Param("id") Long id);


    @Select("SELECT\n" +
            "  f.name AS foodName,\n" +
            "  COUNT(o.id) AS totalOrders\n" +
            "FROM\n" +
            "  foods f,\n" +
            "  orders o\n" +
            "WHERE\n" +
            "  f.id = o.food_id\n" +
            "  AND f.id IN (SELECT id FROM foods)\n" +
            "GROUP BY\n" +
            "  f.name\n" +
            "ORDER BY\n" +
            "  totalOrders DESC, foodName;")
    List<FoodOrderSummaryDTO> listFoodOrder();
}

