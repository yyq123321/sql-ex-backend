package com.yyq.sql.model.vo.food;

import lombok.Data;

/**
 * @description:
 * @author: Yiqi Yu
 * @time: 2023/12/1 16:29
 */
@Data
public class FoodsVO {
    private Long id;

    private String name;

    private Integer category;

    private Long businessId;

    private Double price;

    private String photo;
}
