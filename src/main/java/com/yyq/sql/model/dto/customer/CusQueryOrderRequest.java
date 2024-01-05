package com.yyq.sql.model.dto.customer;

import com.yyq.sql.common.PageRequest;
import lombok.Data;

/**
 * @description:
 * @author: Yiqi Yu
 * @time: 2023/12/8 15:28
 */
@Data
public class CusQueryOrderRequest extends PageRequest {
    private Long customerId;
}
