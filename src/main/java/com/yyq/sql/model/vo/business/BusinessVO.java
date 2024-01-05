package com.yyq.sql.model.vo.business;

import com.yyq.sql.model.vo.UserVO;
import lombok.Data;

/**
 * @description:
 * @author: Yiqi Yu
 * @time: 2023/12/1 15:53
 */
@Data
public class BusinessVO extends UserVO {

    private String phone;

    private String name;

    private String photo;

    private String address;

}
