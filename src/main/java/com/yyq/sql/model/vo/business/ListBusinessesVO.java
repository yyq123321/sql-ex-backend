package com.yyq.sql.model.vo.business;

import com.yyq.sql.model.vo.order.OrdersVO;
import lombok.Data;

import java.util.List;

/**
 * @description:
 * @author: Yiqi Yu
 * @time: 2023/12/8 16:01
 */
@Data
public class ListBusinessesVO {

    private List<BusinessVO> list;

    private Long total;

    public ListBusinessesVO(List<BusinessVO> list, Long total) {
        this.list = list;
        this.total = total;
    }
}
