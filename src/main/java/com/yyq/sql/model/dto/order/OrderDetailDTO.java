package com.yyq.sql.model.dto.order;

import io.swagger.models.auth.In;
import lombok.Data;

/**
 * @description:
 * @author: Yiqi Yu
 * @time: 2023/12/11 10:55
 */
@Data
public class OrderDetailDTO {
    private Long id;
    private String orderNum;
    private Integer status;
    private String remark;
    private Double price;

    /*      */
    private Long customerId;
    private String cusPhone;

    /*       */
    private Long addressId;
    private String consignee;
    private String receiverPhone;
    private String detail;

    /*       */

    private Long riderId;
    private String riderName;
    private String riderPhone;

    /*   */

    private Long foodId;
    private String foodName;
    private Integer foodCategory;
    private Double foodPrice;

    /*     */
    private Long businessId;
    private String businessPhone;
    private String businessName;




}
