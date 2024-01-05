package com.yyq.sql.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yyq.sql.common.PageRequest;
import com.yyq.sql.model.domain.Address;
import com.yyq.sql.model.domain.Businesses;
import com.yyq.sql.model.dto.business.BusinessFoodOrderDTO;
import com.yyq.sql.model.dto.business.BusinessLoginRequest;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
* @author Acer
* @description 针对表【businesses】的数据库操作Mapper
* @createDate 2023-11-24 15:30:03
* @Entity com.yyq.sql.model.domain.Businesses
*/

public interface BusinessesMapper extends BaseMapper<Businesses> {

    @Select("SELECT * FROM businesses WHERE id = #{id}")
    Businesses getById(@Param("id") Long id);

    @Insert("INSERT INTO businesses (phone, name, password, photo, address) " +
            "VALUES (#{phone}, #{name}, #{password}, #{photo}, #{address})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insertBusiness(Businesses business);

    @Update("UPDATE businesses SET phone = #{phone}, name = #{name}, " +
            "password = #{password}, photo = #{photo}, address = #{address} WHERE id = #{id}")
    void updateBusiness(Businesses business);

    @Delete("DELETE FROM businesses WHERE id = #{id}")
    Integer deleteById(@Param("id") Long id);

    @Select("SELECT * FROM businesses " +
            "WHERE phone = #{phone} AND password = #{password}")
    Businesses login(BusinessLoginRequest businessLoginRequest);

    @Select("SELECT * FROM businesses " +
            "ORDER BY update_time " +
            "LIMIT #{offset}, #{pageSize};")
    List<Businesses> listByPage(@Param("offset") Long offset,
                                @Param("pageSize") Long pageSize);

    @Select("SELECT COUNT(*) AS total_count " +
            "FROM businesses;")
    Long count();

    @Select("SELECT\n" +
            "  b.id AS businessId,\n" +
            "  b.name AS businessName,\n" +
            "  f.name AS foodName,\n" +
            "  COUNT(o.id) AS totalOrders\n" +
            "FROM\n" +
            "  businesses b,\n" +
            "  foods f,\n" +
            "  orders o\n" +
            "WHERE\n" +
            "  b.id = f.business_id\n" +
            "  AND f.id = o.food_id\n" +
            "  AND b.id IN (SELECT DISTINCT business_id FROM foods)\n" +
            "GROUP BY\n" +
            "  b.id, b.name, f.name\n" +
            "ORDER BY\n" +
            "  COUNT(o.id) DESC, businessName, foodName;")
    List<BusinessFoodOrderDTO> listBusinessFoodOrder();

}


