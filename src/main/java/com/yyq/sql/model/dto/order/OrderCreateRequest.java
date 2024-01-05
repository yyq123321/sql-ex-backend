package com.yyq.sql.model.dto.order;

import com.yyq.sql.model.vo.address.AddressVO;
import com.yyq.sql.model.vo.business.BusinessVO;
import com.yyq.sql.model.vo.customer.CustomerVO;
import com.yyq.sql.model.vo.food.FoodsVO;
import com.yyq.sql.model.vo.rider.RiderVO;
import lombok.Data;

/**
 * @description:
 * @author: Yiqi Yu
 * @time: 2023/12/1 16:10
 */
@Data
public class OrderCreateRequest {

    private Long customerId;

    private Long riderId;

    private Long businessId;

    private Long foodId;

    private Long addressId;

    private Double price;

    private String remark;
}
