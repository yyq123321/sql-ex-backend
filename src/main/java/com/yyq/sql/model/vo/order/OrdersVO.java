package com.yyq.sql.model.vo.order;

import com.yyq.sql.model.vo.address.AddressVO;
import com.yyq.sql.model.vo.business.BusinessVO;
import com.yyq.sql.model.vo.customer.CustomerVO;
import com.yyq.sql.model.vo.food.FoodsVO;
import com.yyq.sql.model.vo.rider.RiderVO;
import lombok.Data;

/**
 * @description:
 * @author: Yiqi Yu
 * @time: 2023/12/1 16:30
 */
@Data
public class OrdersVO {
    private Long id;

    private String orderNum;

    private Integer status;

    private CustomerVO customer;

    private RiderVO rider;

    private BusinessVO business;

    private FoodsVO food;

    private AddressVO address;

    private Double price;

    private String remark;
}
