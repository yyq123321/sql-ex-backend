package com.yyq.sql.model.vo.rider;

import com.yyq.sql.model.vo.UserVO;
import lombok.Data;

/**
 * @description:
 * @author: Yiqi Yu
 * @time: 2023/12/8 15:09
 */
@Data
public class RiderVO extends UserVO {

    private String name;

    private String phone;

    private String avatar;
}
