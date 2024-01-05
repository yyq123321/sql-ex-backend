package com.yyq.sql.model.dto.rider;

import lombok.Data;

import java.io.Serializable;

/**
 * @description:
 * @author: Yiqi Yu
 * @time: 2023/12/1 16:18
 */
@Data
public class RiderRegisterRequest implements Serializable {
    private String name;
    private String phone;
    private String password;
}
