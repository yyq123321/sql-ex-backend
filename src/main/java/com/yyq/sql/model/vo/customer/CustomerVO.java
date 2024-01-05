package com.yyq.sql.model.vo.customer;

import com.yyq.sql.model.vo.UserVO;
import lombok.Data;

/**
 * @description:
 * @author: Yiqi Yu
 * @time: 2023/12/8 15:08
 */
@Data
public class CustomerVO extends UserVO {

    private String name;

    private String phone;

    private String avatar;
}
