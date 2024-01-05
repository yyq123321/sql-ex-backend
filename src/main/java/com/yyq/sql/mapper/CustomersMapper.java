package com.yyq.sql.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yyq.sql.model.domain.Customers;
import com.yyq.sql.model.dto.business.BusinessLoginRequest;
import com.yyq.sql.model.dto.customer.CustomerLoginRequest;
import org.apache.ibatis.annotations.*;
/**
* @author Acer
* @description 针对表【customers】的数据库操作Mapper
* @createDate 2023-11-24 15:30:03
* @Entity com.yyq.sql.model.domain.Customers
*/


public interface CustomersMapper extends BaseMapper<Customers> {

    @Select("SELECT * FROM customers WHERE id = #{id}")
    Customers getById(long id);

    @Insert("INSERT INTO customers (name, phone, password, avatar) " +
            "VALUES (#{name}, #{phone}, #{password}, #{avatar})")
    void insertCustomer(Customers customer);

    @Update("UPDATE customers SET name = #{name}, phone = #{phone}, " +
            "password = #{password}, avatar = #{avatar} WHERE id = #{id}")
    void updateCustomer(Customers customer);

    @Delete("DELETE FROM customers WHERE id = #{id}")
    void deleteById(@Param("id") Long id);

    @Select("SELECT * FROM customers " +
            "WHERE phone = #{phone} AND password = #{password}")
    Customers login(CustomerLoginRequest customerLoginRequest);
}

