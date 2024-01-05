package com.yyq.sql.model.dto.address;

import lombok.Data;

/**
 * @description:
 * @author: Yiqi Yu
 * @time: 2023/12/8 14:57
 */
@Data
public class AddressUpdateRequest {
    private Long id;

    private Long customerId;

    private String consignee;

    private Integer sex;

    private String phone;

    private String detail;
}
