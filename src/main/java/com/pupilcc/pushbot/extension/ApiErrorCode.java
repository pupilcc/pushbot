package com.pupilcc.pushbot.extension;

/**
 * REST API 错误码
 * @author MyBatis Plus
 */
public enum ApiErrorCode implements IErrorCode{
    /**
     * 失败
     */
    FAILED(-1L, "failed"),
    /**
     * 成功
     */
    SUCCESS(0L, "success"),
    /**
     * 用户不存在
     */
    USER_NOT_EXIST(-1L, "user not exist"),
    /**
     * text 为空
     */
    TEXT_NULL(-1L, "text null"),

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
