package com.yyq.sql.model.dto.rider;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yyq.sql.common.PageRequest;
import lombok.Data;

/**
 * @description:
 * @author: Yiqi Yu
 * @time: 2023/12/8 15:29
 */
@Data
public class RiderQueryOrderRequest extends PageRequest {
    private Long riderId;
}
