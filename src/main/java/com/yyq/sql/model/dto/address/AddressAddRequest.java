package com.yyq.sql.model.dto.address;

import lombok.Data;

import java.util.Date;

/**
 * @description:
 * @author: Yiqi Yu
 * @time: 2023/12/1 15:57
 */
@Data
public class AddressAddRequest {
    private Long customerId;

    private String consignee;

    private Integer sex;

    private String phone;

    private String detail;
}
