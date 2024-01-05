package com.yyq.sql.model.dto.food;

import com.yyq.sql.common.PageRequest;
import lombok.Data;

/**
 * @description:
 * @author: Yiqi Yu
 * @time: 2023/12/8 23:04
 */
@Data
public class FoodsQueryRequest extends PageRequest {
    private Long businessId;
}
