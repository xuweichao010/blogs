package com.cccloud.blogs.commons.exceptions;

/**
 * 创建人：徐卫超
 * 创建时间：2018/8/15  15:56
 * 功能：业务异常,只需要对这类异常进行常规操作,返回到调用者即可
 */
public class BusinessException extends BaseExpception {
    private Integer code = 400;

    public BusinessException(String message, String... desc) {
        super(message, desc);
    }

    public BusinessException(String message, Integer code, String... desc) {
        super(message, desc);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
