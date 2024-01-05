package com.yyq.sql.model.dto.business;

import lombok.Data;

/**
 * @description:
 * @author: Yiqi Yu
 * @time: 2023/12/29 20:00
 */
@Data
public class BusinessFoodOrderDTO {
    public int businessId;
    public String businessName;
    public String foodName;
    public int totalOrders;
}

