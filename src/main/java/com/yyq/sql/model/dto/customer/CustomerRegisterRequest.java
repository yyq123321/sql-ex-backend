package com.yyq.sql.model.dto.customer;

import lombok.Data;

import java.io.Serializable;

/**
 * @description:
 * @author: Yiqi Yu
 * @time: 2023/12/1 16:19
 */

@Data
public class CustomerRegisterRequest implements Serializable {
    private String name;
    private String phone;
    private String password;
}
