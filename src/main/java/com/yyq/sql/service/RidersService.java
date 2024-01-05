package com.yyq.sql.service;

import com.yyq.sql.model.domain.Riders;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yyq.sql.model.dto.rider.RiderLoginRequest;
import com.yyq.sql.model.dto.rider.RiderRegisterRequest;

import javax.servlet.http.HttpServletRequest;

/**
* @author Acer
* @description 针对表【riders】的数据库操作Service
* @createDate 2023-11-24 15:30:03
*/
public interface RidersService extends IService<Riders> {

    Integer login(RiderLoginRequest riderLoginRequest, HttpServletRequest request);

    Integer register(RiderRegisterRequest registerRequest);

    Riders getRidersById(long id);
}
