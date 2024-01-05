package com.yyq.sql.model.vo.order;

import com.yyq.sql.model.domain.Foods;
import lombok.Data;

import java.util.List;

/**
 * @description:
 * @author: Yiqi Yu
 * @time: 2023/12/8 16:01
 */
@Data
public class ListOrdersVO {

    private List<OrdersVO> list;

    private Long total;

    public ListOrdersVO(List<OrdersVO> list, Long total) {
        this.list = list;
        this.total = total;
    }
}
