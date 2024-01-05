package com.yyq.sql.model.dto.food;

import lombok.Data;

/**
 * @description:
 * @author: Yiqi Yu
 * @time: 2023/12/29 20:00
 */
@Data
public class FoodOrderSummaryDTO {
    public String foodName;
    public int totalOrders;
}

