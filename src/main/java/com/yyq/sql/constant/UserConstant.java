package com.yyq.sql.constant;

/**
 * 用户常量
 *
 */
public interface UserConstant {

    /**
     * 用户登录态键
     */
    String BUSINESS_LOGIN_STATE = "business_login";

    /**
     * 用户登录态键
     */
    String CUSTOMER_LOGIN_STATE = "customer_login";

    /**
     * 用户登录态键
     */
    String RIDER_LOGIN_STATE = "rider_login";

    //  region 权限

    /**
     * 默认角色
     */
    String DEFAULT_ROLE = "user";

    /**
     * 管理员角色
     */
    String ADMIN_ROLE = "admin";

    /**
     * 骑手
     */
    String RIDER_ROLE = "rider";

    /**
     * 商家
     */
    String BUSINESS_ROLE = "business";

    // endregion
}
