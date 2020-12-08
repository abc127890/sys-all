package com.ayl.advert.common.response;

/**
 * @author wfd
 */
public class BusinessException extends RuntimeException {

    private Result.Code code;

    private String msg;

    public BusinessException(Result.Code code) {
        super(code.getExplain());
        this.code = code;
        this.msg=code.getExplain();
    }
    public BusinessException(Result.Code code, String msg) {
        super(msg);
        this.code = code;
        this.msg=msg;
    }

    public Result.Code getCode() {
        return code;
    }

    public void setCode(Result.Code code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }
}
