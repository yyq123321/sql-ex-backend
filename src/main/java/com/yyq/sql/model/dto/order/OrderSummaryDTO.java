package com.yyq.sql.model.dto.order;

import lombok.Data;

/**
 * @description:
 * @author: Yiqi Yu
 * @time: 2023/12/29 19:59
 */
@Data
public class OrderSummaryDTO {
    public String foodName;
    public int totalOrders;
    public String businessName;

}
