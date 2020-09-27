package com.pupilcc.pushbot.extension;

import java.io.Serializable;
import java.util.Optional;

/**
 * REST API 返回结果
 * @author Mybatis Plus
 */
public class ApiResult<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 业务错误码
     */
    private long code;

    /**
     * 描述
     */
    private String msg;

    /**
     * 结果集
     */
    private T data;

    public ApiResult() {
    }

    public ApiResult(IErrorCode errorCode) {
        errorCode = Optional.ofNullable(errorCode).orElse(ApiErrorCode.FAILED);
        this.code = errorCode.getCode();
        this.msg = errorCode.getMsg();
    }

    public static <T> ApiResult<T> restResult(T data, IErrorCode errorCode) {
        return restResult(data, errorCode.getCode(), errorCode.getMsg());
    }

    private static <T> ApiResult<T> restResult(T data, long code, String msg) {
        ApiResult<T> apiResult = new ApiResult<>();
        apiResult.setCode(code);
        apiResult.setData(data);
        apiResult.setMsg(msg);
        return apiResult;
    }

    /* success */

    public static <T> ApiResult<T> success() {
        return restResult(null, ApiErrorCode.SUCCESS);
    }

    public static <T> ApiResult<T> success(T data) {
        ApiErrorCode aec = ApiErrorCode.SUCCESS;
        if (data instanceof Boolean && Boolean.FALSE.equals(data)) {
            aec = ApiErrorCode.FAILED;
        }

        return restResult(data, aec);
    }

    /* failed */

    public static <T> ApiResult<T> failed(String msg) {
        return restResult(null, ApiErrorCode.FAILED.getCode(), msg);
    }

    public static <T> ApiResult<T> failed(IErrorCode errorCode) {
        return restResult(null, errorCode);
    }

    public boolean ok() {
        return ApiErrorCode.SUCCESS.getCode() == this.code;
    }

    /**
     * 服务间调用非业务正常，异常直接释放
     */
    public T serviceData() {
        if (!this.ok()) {
            throw new ApiException(this.msg);
        } else {
            return this.data;
        }
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
