package com.sys.common.response;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author wfd
 */
public class Result {

    /**
     * 成功code
     */
    private final static Result.Code SUCCESS_CODE = Code.SUCCESS;
    /**
     * 响应消息
     */
    private String msg = "success";

    @JsonIgnore
    @JSONField(serialize = false)
    private Code code;

    public Result() {
        this(Code.SUCCESS);
    }

    public Result(Code code) {
        this(code, code.getExplain());
    }

    public Result(Code code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public boolean isSuccess() {
        return SUCCESS_CODE.equals(code);
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Code getCode() {
        return this.code;
    }

    @JsonProperty("code")
    @JSONField(name = "code")
    public String getResultCode() {
        return this.code.code;
    }

    public enum Code {

        SUCCESS("0", "成功"),
        SYSTEM_ERROR("-1", "系统繁忙,请稍后再试"),
        PARAM_ERROR("100", "参数异常"),
        NOT_FOUND("101", "未找到"),
        NOT_LOGIN("NotLogin", "您没有登录或登录超时，请重新登录"),
        NO_RIGHT("NoRight", "对不起，您没有该功能操作权限，请联系管理员");

        Code(String code, String explain) {
            this.code = code;
            this.explain = explain;
        }

        private String code;

        private String explain;

        public String getExplain() {
            return explain;
        }

        public void setExplain(String explain) {
            this.explain = explain;
        }

        public String getCode() {
            return code;
        }

        @Override
        public String toString() {
            return JSON.toJSONString(this);
        }
    }

    public static Result ok(){
        return new Result();
    }

    public static Result error(Code code){
        return new Result(code);
    }
}
