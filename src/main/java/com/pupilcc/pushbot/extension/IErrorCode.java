package com.pupilcc.pushbot.extension;

/**
 * REST API 错误码接口
 * @author Mybatis Plus
 */
public interface IErrorCode {
    /**
     * 错误编码 -1、失败 0、成功
     */
    long getCode();

    /**
     * 错误描述
     */
    String getMsg();
}
