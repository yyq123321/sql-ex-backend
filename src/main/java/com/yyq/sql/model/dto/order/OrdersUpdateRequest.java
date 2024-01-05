package com.yyq.sql.model.dto.order;

import lombok.Data;

/**
 * @description:
 * @author: Yiqi Yu
 * @time: 2023/12/1 16:10
 */
@Data
public class OrdersUpdateRequest {
    private Long id;
    private Long riderId;

    private Integer status;
}
