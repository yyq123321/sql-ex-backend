package com.yyq.sql.model.vo.food;

import com.yyq.sql.model.domain.Address;
import com.yyq.sql.model.domain.Foods;
import lombok.Data;

import java.util.List;

/**
 * @description:
 * @author: Yiqi Yu
 * @time: 2023/12/8 16:01
 */
@Data
public class ListFoodsVO {

    private List<Foods> list;

    private Long total;

    public ListFoodsVO(List<Foods> list, Long total) {
        this.list = list;
        this.total = total;
    }
}
