package com.yyq.sql.model.dto.address;

import com.yyq.sql.common.PageRequest;
import lombok.Data;

/**
 * @description:
 * @author: Yiqi Yu
 * @time: 2023/12/26 16:59
 */
@Data
public class ListAddressPageRequest extends PageRequest {
    private Long customerId;
}
