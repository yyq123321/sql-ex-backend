package com.yyq.sql.model.dto.business;

import com.yyq.sql.common.PageRequest;
import lombok.Data;

/**
 * @description:
 * @author: Yiqi Yu
 * @time: 2023/12/8 15:26
 */
@Data
public class BizQueryOrderRequest extends PageRequest {
    private Long businessId;
}
