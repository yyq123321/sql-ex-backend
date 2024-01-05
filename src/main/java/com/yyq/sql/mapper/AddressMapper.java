package com.yyq.sql.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yyq.sql.common.PageRequest;
import com.yyq.sql.model.domain.Address;
import com.yyq.sql.model.domain.Orders;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
* @author Acer
* @description 针对表【address】的数据库操作Mapper
* @createDate 2023-11-24 15:30:03
* @Entity com.yyq.sql.model.domain.Address
*/


public interface AddressMapper extends BaseMapper<Address> {

    // 根据 consignee 名称查询地址
    @Select("SELECT * FROM address WHERE consignee = #{consignee}")
    Address findByConsignee(@Param("consignee") String consignee);

    // 插入地址信息
    @Insert("INSERT INTO address (customer_id, consignee, sex, phone, detail) " +
            "VALUES (#{customerId}, #{consignee}, #{sex}, #{phone}, #{detail})")
    Long insertAddress(Address address);

    // 更新地址信息
    @Update("UPDATE address SET consignee = #{consignee}, sex = #{sex}, " +
            "phone = #{phone}, detail = #{detail} WHERE id = #{id}")
    Integer updateAddress(Address address);

    @Select("SELECT * FROM address " +
            "WHERE customer_id = #{customerId} " +
            "ORDER BY update_time " +
            "LIMIT #{offset}, #{pageSize};")
    List<Address> listByPage(@Param("customerId") Long customerId,
                             @Param("offset") Long offset,
                             @Param("pageSize") Long pageSize);

    @Select("SELECT COUNT(*) AS total_count " +
            "FROM address " +
            "WHERE customer_id = #{customerId};" )
    Long count(@Param("customerId") Long customerId);

    // 根据 ID 删除地址信息
    @Delete("DELETE FROM address WHERE id = #{id}")
    Integer deleteAddressById(@Param("id") Long id);
}

