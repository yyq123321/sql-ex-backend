package com.yyq.sql.model.dto.business;

import lombok.Data;

import java.io.Serializable;

/**
 * @description:
 * @author: Yiqi Yu
 * @time: 2023/12/1 16:20
 */

@Data
public class BusinessRegisterRequest implements Serializable {
    private String name;
    private String phone;
    private String password;
    private String address;
    private String photo;

}
