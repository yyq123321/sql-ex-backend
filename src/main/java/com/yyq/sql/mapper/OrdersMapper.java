package com.yyq.sql.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yyq.sql.common.PageRequest;
import com.yyq.sql.model.domain.Address;
import com.yyq.sql.model.domain.Orders;
import com.yyq.sql.model.dto.business.BizQueryOrderRequest;
import com.yyq.sql.model.dto.customer.CusQueryOrderRequest;
import com.yyq.sql.model.dto.order.OrderDetailDTO;
import com.yyq.sql.model.dto.order.OrderSummaryDTO;
import com.yyq.sql.model.dto.order.OrdersUpdateRequest;
import com.yyq.sql.model.dto.rider.RiderQueryOrderRequest;
import com.yyq.sql.model.vo.order.OrdersVO;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
* @author Acer
* @description 针对表【orders】的数据库操作Mapper
* @createDate 2023-11-24 15:30:03
* @Entity com.yyq.sql.model.domain.Orders
*/

public interface OrdersMapper extends BaseMapper<Orders> {

    @Select("SELECT * FROM orders WHERE id = #{id}")
    Orders getById(@Param("id") Long id);

    @Insert("INSERT INTO orders (order_num, status, customer_id, rider_id, business_id, food_id, address_id, price, remark) " +
            "VALUES (#{orderNum}, #{status}, #{customerId}, #{riderId}, #{businessId}, #{foodId}, #{addressId}, #{price}, #{remark})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    Integer createOrder(Orders order);

    @Update("UPDATE orders " +
            "SET status = #{status}, " +
            "    rider_id = CASE " +
            "        WHEN #{riderId} IS NOT NULL THEN #{riderId} " +
            "        ELSE rider_id " +
            "    END " +
            "WHERE id = #{id} AND #{status} >= status; ")
    Integer updateOrder(OrdersUpdateRequest request);

    @Delete("DELETE FROM orders WHERE id = #{id}")
    void deleteOrderById(@Param("id") Long id);

    @Select("SELECT * FROM orders " +
            "ORDER BY update_time " +
            "LIMIT ((#{current} - 1)*#{pageSize}), #{pageSize};")
    List<Orders> listByPage(PageRequest pageRequest);

    @Select("SELECT COUNT(*) FROM orders;")
    Long countOrders();


    @Select("SELECT\n" +
            "    orders.id,\n" +
            "    orders.order_num AS orderNum,\n" +
            "    orders.status,\n" +
            "    orders.remark,\n" +
            "    orders.price,\n" +
            "    customers.id AS customerId,\n" +
            "    customers.phone AS cusPhone,\n" +
            "    address.id AS addressId,\n" +
            "    address.consignee AS consignee,\n" +
            "    address.phone AS receiverPhone,\n" +
            "    address.detail AS detail,\n" +
            "    CASE\n" +
            "        WHEN orders.rider_id IS NOT NULL THEN riders.id\n" +
            "        ELSE NULL\n" +
            "    END AS riderId,\n" +
            "    CASE\n" +
            "        WHEN orders.rider_id IS NOT NULL THEN riders.name\n" +
            "        ELSE NULL\n" +
            "    END AS riderName,\n" +
            "    CASE\n" +
            "        WHEN orders.rider_id IS NOT NULL THEN riders.phone\n" +
            "        ELSE NULL\n" +
            "    END AS riderPhone,\n" +
            "    foods.id AS foodId,\n" +
            "    foods.name AS foodName,\n" +
            "    foods.category AS foodCategory,\n" +
            "    foods.price AS foodPrice,\n" +
            "    businesses.id AS businessId,\n" +
            "    businesses.name AS businessName,\n" +
            "    businesses.phone AS businessPhone \n" +
            "FROM orders\n" +
            "JOIN customers ON orders.customer_id = customers.id\n" +
            "LEFT JOIN riders ON orders.rider_id = riders.id\n" +
            "JOIN businesses ON orders.business_id = businesses.id\n" +
            "JOIN foods ON orders.food_id = foods.id\n" +
            "JOIN address ON orders.address_id = address.id\n" +
            "WHERE orders.id = #{id};")
    OrderDetailDTO showOrderInfo(@Param("id") Long id);



    @Select("SELECT " +
            "o.id AS id, " +
            "o.order_num AS orderNum, " +
            "o.status AS status " +
            "FROM orders o " +
            "WHERE o.customer_id = #{customerId} " +
            "LIMIT #{offset}, #{pageSize};")
    List<Orders> listCusMy(@Param("customerId") Long customerId, @Param("offset") Long offset,
                           @Param("pageSize") Long pageSize);

    @Select("SELECT COUNT(*) " +
            "FROM orders o " +
            "WHERE o.customer_id = #{customerId};")
    Long countCusMy(Long customerId);

    @Select("SELECT " +
            "o.id AS id, " +
            "o.order_num AS orderNum, " +
            "o.status AS status " +
            "FROM orders o " +
            "WHERE o.business_id = #{bizId} " +
            "LIMIT #{offset}, #{pageSize};")
    List<Orders> listBizMy(@Param("bizId") Long bizId, @Param("offset") Long offset,
                           @Param("pageSize") Long pageSize);

    @Select("SELECT COUNT(*) " +
            "FROM orders o " +
            "WHERE o.business_id = #{bizId};")
    Long countBizMy(Long bizId);

    @Select("SELECT " +
            "o.id AS id, " +
            "o.order_num AS orderNum, " +
            "o.status AS status " +
            "FROM orders o " +
            "WHERE o.rider_id = #{riderId} " +
            "LIMIT #{offset}, #{pageSize};")
    List<Orders> listRiderMy(@Param("riderId") Long riderId, @Param("offset") Long offset,
                           @Param("pageSize") Long pageSize);

    @Select("SELECT COUNT(*) " +
            "FROM orders o " +
            "WHERE o.rider_id = #{riderId};")
    Long countRiderMy(Long riderId);

    @Select("SELECT " +
            "o.id AS id, " +
            "o.order_num AS orderNum, " +
            "o.status AS status " +
            "FROM orders o " +
            "WHERE o.rider_id IS NULL " +
            "LIMIT #{offset}, #{pageSize};")
    List<Orders> listNoRider(@Param("offset") Long offset,
                             @Param("pageSize") Long pageSize);

    @Select("SELECT COUNT(*) " +
            "FROM orders o " +
            "WHERE o.rider_id IS NULL;")
    Long countNoRider();

    @Select("SELECT\n" +
            "  b.name AS businessName,\n" +
            "  f.name AS foodName,\n" +
            "  COUNT(f.id) AS totalOrders\n" +
            "FROM\n" +
            "  orders o\n" +
            "JOIN\n" +
            "  businesses b ON b.id = o.business_id\n" +
            "JOIN\n" +
            "  foods f ON o.food_id = f.id\n" +
            "GROUP BY\n" +
            "  b.name, f.name\n" +
            "ORDER BY\n" +
            "  totalOrders DESC;")
    List<OrderSummaryDTO> listOrderSummary();
}

