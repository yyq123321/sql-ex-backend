package com.yyq.sql.model.dto.rider;

import lombok.Data;

import java.io.Serializable;

/**
 * @description:
 * @author: Yiqi Yu
 * @time: 2023/12/1 15:55
 */
@Data
public class RiderLoginRequest implements Serializable {

    private String phone;

    private String password;
}
