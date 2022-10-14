package com.pupilcc.pushbot.extension;

import com.pupilcc.common.rest.IErrorCode;

/**
 * REST API 错误码
 *
 * @author MyBatis Plus
 */
public enum ApiErrorCode implements IErrorCode {
    /**
     * 参数为空
     */
    PARAMETER_NULL(-1L, "parameter null"),
    /**
     * 用户不存在
     */
    USER_NOT_EXIST(-1L, "user not exist"),
    /**
     * 文字为空
     */
    TEXT_NULL(-1L, "text null"),
    /**
     * 图片为空
     */
    IMG_NULL(-1L, "image null"),
    /**
     * 模板不存在
     */
    TEMPLATE_NOT_EXIST(-1L, "template not exist"),

    ;

    private final long code;
    private final String msg;

    ApiErrorCode(final long code, final String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public long getCode() {
        return this.code;
    }

    @Override
    public String getMsg() {
        return this.msg;
    }

    @Override
    public String toString() {
        return String.format(" ErrorCode:{code=%s, msg=%s} ", this.code, this.msg);
    }
}
