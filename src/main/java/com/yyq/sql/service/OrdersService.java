package com.yyq.sql.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yyq.sql.common.PageRequest;
import com.yyq.sql.model.domain.Orders;
import com.yyq.sql.model.dto.business.BizQueryOrderRequest;
import com.yyq.sql.model.dto.customer.CusQueryOrderRequest;
import com.yyq.sql.model.dto.order.OrderCreateRequest;
import com.yyq.sql.model.dto.order.OrderSummaryDTO;
import com.yyq.sql.model.dto.order.OrdersUpdateRequest;
import com.yyq.sql.model.dto.rider.RiderQueryOrderRequest;
import com.yyq.sql.model.vo.order.ListOrdersVO;
import com.yyq.sql.model.vo.order.OrdersVO;

import java.util.List;

/**
* @author Acer
* @description 针对表【orders】的数据库操作Service
* @createDate 2023-11-24 15:30:03
*/
public interface OrdersService extends IService<Orders> {

    Integer create(OrderCreateRequest orderCreateRequest);

    ListOrdersVO list(PageRequest ordersQueryRequest);

    ListOrdersVO listBizMy(BizQueryOrderRequest bizQueryOrderRequest);

    ListOrdersVO listCusMy(CusQueryOrderRequest cusQueryOrderRequest);

    ListOrdersVO listRiderMy(RiderQueryOrderRequest riderQueryOrderRequest);

    OrdersVO getOrdersById(long id);

    Integer updateOrder(OrdersUpdateRequest ordersUpdateRequest);

    ListOrdersVO listNoRiderOrder(PageRequest ordersQueryRequest);

    List<OrderSummaryDTO> listOrderSummary();
}
