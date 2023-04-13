package com.yyq.usercenter.exception;

import com.yyq.usercenter.common.ErrorCode;

/**
 * @description: 异常处理类
 * @author: Yiqi Yu
 * @time: 2023/4/11 17:04
 */
public class BusinessException extends RuntimeException {
    private String description;
    private int code;

    public BusinessException(String message, int code, String description) {
        super(message);
        this.description = description;
        this.code = code;
    }

    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.description = errorCode.getDescription();
        this.code = errorCode.getCode();
    }
    public BusinessException(ErrorCode errorCode, String description) {
        super(errorCode.getMessage());
        this.description = description;
        this.code = errorCode.getCode();
    }

    public String getDescription() {
        return description;
    }

    public int getCode() {
        return code;
    }
}
