package com.yyq.sql.service;

import com.yyq.sql.common.PageRequest;
import com.yyq.sql.model.domain.Businesses;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yyq.sql.model.dto.business.BusinessFoodOrderDTO;
import com.yyq.sql.model.dto.business.BusinessLoginRequest;
import com.yyq.sql.model.vo.business.ListBusinessesVO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
* @author Acer
* @description 针对表【businesses】的数据库操作Service
* @createDate 2023-11-24 15:30:03
*/
public interface BusinessesService extends IService<Businesses> {

    Integer login(BusinessLoginRequest businessLoginRequest, HttpServletRequest request);

    Businesses getBizById(long id);

    ListBusinessesVO listBiz(PageRequest pageRequest);

    List<BusinessFoodOrderDTO> listBusinessFoodOrder();
}
