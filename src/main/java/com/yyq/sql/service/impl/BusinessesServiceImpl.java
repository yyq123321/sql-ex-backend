package com.yyq.sql.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yyq.sql.common.PageRequest;
import com.yyq.sql.model.domain.Businesses;
import com.yyq.sql.model.dto.business.BusinessFoodOrderDTO;
import com.yyq.sql.model.dto.business.BusinessLoginRequest;
import com.yyq.sql.model.vo.business.BusinessVO;
import com.yyq.sql.model.vo.business.ListBusinessesVO;
import com.yyq.sql.service.BusinessesService;
import com.yyq.sql.mapper.BusinessesMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.stream.Collectors;

import static com.yyq.sql.constant.UserConstant.BUSINESS_LOGIN_STATE;

/**
* @author Acer
* @description 针对表【businesses】的数据库操作Service实现
* @createDate 2023-11-24 15:30:03
*/
@Service
public class BusinessesServiceImpl extends ServiceImpl<BusinessesMapper, Businesses>
implements BusinessesService{

    @Resource
    private BusinessesMapper businessesMapper;

    @Override
    public Integer login(BusinessLoginRequest businessLoginRequest, HttpServletRequest request) {
        Businesses businesses = businessesMapper.login(businessLoginRequest);
        if(businesses == null) {
            return 0;
        }
        businesses.setPassword(null);
        //记录用户的登录态
        request.getSession().setAttribute(BUSINESS_LOGIN_STATE, businesses);
        return 1;
    }

    @Override
    public Businesses getBizById(long id) {
        return businessesMapper.getById(id);
    }

    @Override
    public ListBusinessesVO listBiz(PageRequest pageRequest) {
        long pageSize = pageRequest.getPageSize();
        long current = pageRequest.getCurrent();
        List<Businesses> list = businessesMapper.listByPage((current - 1) * pageSize,
                pageSize);
        List<BusinessVO> voList = list.stream().map(businesses -> {
            BusinessVO businessVO = new BusinessVO();
            BeanUtils.copyProperties(businesses, businessVO);
            return businessVO;
        }).collect(Collectors.toList());
        Long count = businessesMapper.count();
        ListBusinessesVO listBusinessesVO = new ListBusinessesVO(voList, count);
        return listBusinessesVO;
    }

    @Override
    public List<BusinessFoodOrderDTO> listBusinessFoodOrder() {
        return businessesMapper.listBusinessFoodOrder();
    }
}
