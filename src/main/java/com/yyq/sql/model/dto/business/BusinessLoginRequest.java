package com.yyq.sql.model.dto.business;

import lombok.Data;

import java.io.Serializable;

/**
 * @description:
 * @author: Yiqi Yu
 * @time: 2023/12/1 15:56
 */

@Data
public class BusinessLoginRequest implements Serializable {

    private String phone;

    private String password;
}
