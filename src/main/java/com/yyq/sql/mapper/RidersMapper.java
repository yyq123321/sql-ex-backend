package com.yyq.sql.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yyq.sql.model.domain.Riders;
import com.yyq.sql.model.dto.business.BusinessLoginRequest;
import com.yyq.sql.model.dto.rider.RiderLoginRequest;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.*;
/**
* @author Acer
* @description 针对表【riders】的数据库操作Mapper
* @createDate 2023-11-24 15:30:03
* @Entity com.yyq.sql.model.domain.Riders
*/

public interface RidersMapper extends BaseMapper<Riders> {

    @Select("SELECT * FROM riders WHERE id = #{id}")
    Riders getById(@Param("id") Long id);

    @Insert("INSERT INTO riders (name, phone, password, avatar) " +
            "VALUES (#{name}, #{phone}, #{password}, #{avatar})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    Integer register(Riders rider);

    @Update("UPDATE riders SET name = #{name}, phone = #{phone}, " +
            "password = #{password}, avatar = #{avatar} WHERE id = #{id}")
    void updateRider(Riders rider);

    @Delete("DELETE FROM riders WHERE id = #{id}")
    void deleteRiderById(@Param("id") Long id);

    @Select("SELECT * FROM riders " +
            "WHERE phone = #{phone} AND password = #{password}")
    Riders login(RiderLoginRequest riderLoginRequest);
}

