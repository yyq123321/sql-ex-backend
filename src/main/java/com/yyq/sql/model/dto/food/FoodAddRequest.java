package com.yyq.sql.model.dto.food;

import lombok.Data;

/**
 * @description:
 * @author: Yiqi Yu
 * @time: 2023/12/1 15:58
 */
@Data
public class FoodAddRequest {
    private Long id;

    private String name;

    private Integer category;

    private Long businessId;

    private Double price;

    private String photo;
}
