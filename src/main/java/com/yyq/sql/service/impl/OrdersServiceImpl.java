package com.yyq.sql.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yyq.sql.common.PageRequest;
import com.yyq.sql.model.domain.Customers;
import com.yyq.sql.model.domain.Orders;
import com.yyq.sql.model.dto.business.BizQueryOrderRequest;
import com.yyq.sql.model.dto.customer.CusQueryOrderRequest;
import com.yyq.sql.model.dto.order.OrderCreateRequest;
import com.yyq.sql.model.dto.order.OrderDetailDTO;
import com.yyq.sql.model.dto.order.OrderSummaryDTO;
import com.yyq.sql.model.dto.order.OrdersUpdateRequest;
import com.yyq.sql.model.dto.rider.RiderQueryOrderRequest;
import com.yyq.sql.model.vo.address.AddressVO;
import com.yyq.sql.model.vo.business.BusinessVO;
import com.yyq.sql.model.vo.customer.CustomerVO;
import com.yyq.sql.model.vo.food.FoodsVO;
import com.yyq.sql.model.vo.order.ListOrdersVO;
import com.yyq.sql.model.vo.order.OrdersVO;
import com.yyq.sql.model.vo.rider.RiderVO;
import com.yyq.sql.service.OrdersService;
import com.yyq.sql.mapper.OrdersMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
* @author Acer
* @description 针对表【orders】的数据库操作Service实现
* @createDate 2023-11-24 15:30:03
*/
@Service
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders>
implements OrdersService{

    @Resource
    private OrdersMapper ordersMapper;

    @Override
    public Integer create(OrderCreateRequest orderCreateRequest) {
        Orders orders = new Orders();
        BeanUtils.copyProperties(orderCreateRequest, orders);
        orders.setOrderNum(UUID.randomUUID().toString());
        orders.setStatus(0);
        Integer result = ordersMapper.createOrder(orders);
        return result;
    }

    @Override
    public ListOrdersVO list(PageRequest ordersQueryRequest) {
        List<Orders> orders = ordersMapper.listByPage(ordersQueryRequest);
        Long count = ordersMapper.countOrders();
        return getListOrdersVO(orders, count);
    }

    @Override
    public ListOrdersVO listBizMy(BizQueryOrderRequest bizQueryOrderRequest) {
        long current = bizQueryOrderRequest.getCurrent();
        long pageSize = bizQueryOrderRequest.getPageSize();
        Long businessId = bizQueryOrderRequest.getBusinessId();
        List<Orders> orders = ordersMapper.listBizMy(businessId, (current - 1) * pageSize, pageSize);
        Long count = ordersMapper.countBizMy(businessId);
        return getListOrdersVO(orders, count);
    }

    @Override
    public ListOrdersVO listCusMy(CusQueryOrderRequest cusQueryOrderRequest) {
        long current = cusQueryOrderRequest.getCurrent();
        long pageSize = cusQueryOrderRequest.getPageSize();
        Long customerId = cusQueryOrderRequest.getCustomerId();
        List<Orders> orders = ordersMapper.listCusMy(customerId,
                (current - 1) * pageSize,
                pageSize);
        Long count = ordersMapper.countCusMy(customerId);
        return getListOrdersVO(orders, count);
    }

    @Override
    public ListOrdersVO listRiderMy(RiderQueryOrderRequest riderQueryOrderRequest) {
        Long riderId = riderQueryOrderRequest.getRiderId();
        long current = riderQueryOrderRequest.getCurrent();
        long pageSize = riderQueryOrderRequest.getPageSize();
        List<Orders> orders = ordersMapper.listRiderMy(riderId, (current - 1) * pageSize, pageSize);
        Long count = ordersMapper.countRiderMy(riderId);
        return getListOrdersVO(orders, count);
    }

    @Override
    public OrdersVO getOrdersById(long id) {
        OrderDetailDTO orderDetailDTO = ordersMapper.showOrderInfo(id);
        return orderDtoToVO(orderDetailDTO);
    }

    private OrdersVO orderDtoToVO(OrderDetailDTO orderDetailDTO) {
        OrdersVO ordersVO = new OrdersVO();
        ordersVO.setId(orderDetailDTO.getId());
        ordersVO.setStatus(orderDetailDTO.getStatus());
        ordersVO.setPrice(orderDetailDTO.getPrice());
        ordersVO.setRemark(orderDetailDTO.getRemark());
        ordersVO.setOrderNum(orderDetailDTO.getOrderNum());

        RiderVO riderVO = new RiderVO();
        riderVO.setId(orderDetailDTO.getRiderId());
        riderVO.setName(orderDetailDTO.getRiderName());
        riderVO.setPhone(orderDetailDTO.getRiderPhone());
        ordersVO.setRider(riderVO);

        AddressVO addressVO = new AddressVO();
        addressVO.setId(orderDetailDTO.getAddressId());
        addressVO.setConsignee(orderDetailDTO.getConsignee());
        addressVO.setDetail(orderDetailDTO.getDetail());
        addressVO.setPhone(orderDetailDTO.getReceiverPhone());
        ordersVO.setAddress(addressVO);

        FoodsVO foodsVO = new FoodsVO();
        foodsVO.setId(orderDetailDTO.getFoodId());
        foodsVO.setName(orderDetailDTO.getFoodName());
        foodsVO.setPrice(orderDetailDTO.getFoodPrice());
        foodsVO.setCategory(orderDetailDTO.getFoodCategory());
        ordersVO.setFood(foodsVO);

        BusinessVO businessVO = new BusinessVO();
        businessVO.setId(orderDetailDTO.getBusinessId());
        businessVO.setPhone(orderDetailDTO.getBusinessPhone());
        businessVO.setName(orderDetailDTO.getBusinessName());
        ordersVO.setBusiness(businessVO);

        CustomerVO customerVO = new CustomerVO();
        customerVO.setId(orderDetailDTO.getCustomerId());
        customerVO.setPhone(orderDetailDTO.getCusPhone());
        ordersVO.setCustomer(customerVO);

        return ordersVO;
    }

    @Override
    public Integer updateOrder(OrdersUpdateRequest ordersUpdateRequest) {
        return ordersMapper.updateOrder(ordersUpdateRequest);
    }

    @Override
    public ListOrdersVO listNoRiderOrder(PageRequest ordersQueryRequest) {
        long current = ordersQueryRequest.getCurrent();
        long pageSize = ordersQueryRequest.getPageSize();
        List<Orders> orders = ordersMapper.listNoRider((current - 1) * pageSize, pageSize);
        Long count = ordersMapper.countNoRider();
        return getListOrdersVO(orders, count);
    }

    @Override
    public List<OrderSummaryDTO> listOrderSummary() {
        return ordersMapper.listOrderSummary();
    }

    private ListOrdersVO getListOrdersVO(List<Orders> list, Long count) {
        List<OrdersVO> voList = list.stream().map(orders -> {
            OrdersVO ordersVO = new OrdersVO();
            BeanUtils.copyProperties(orders, ordersVO);
            return ordersVO;
        }).collect(Collectors.toList());
        return new ListOrdersVO(voList, count);
    }

}
