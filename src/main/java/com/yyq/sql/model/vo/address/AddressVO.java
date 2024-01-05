package com.yyq.sql.model.vo.address;

import lombok.Data;

/**
 * @description:
 * @author: Yiqi Yu
 * @time: 2023/12/8 15:01
 */
@Data
public class AddressVO {
    private Long id;

    private Long customerId;

    private String consignee;

    private Integer sex;

    private String phone;

    private String detail;
}
