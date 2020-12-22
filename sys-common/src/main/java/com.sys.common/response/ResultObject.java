package com.sys.common.response;

import com.sys.common.response.Result;

/**
 * @author wfd
 */
public class ResultObject<T> extends Result {

    public ResultObject() {
        super();
    }

    public ResultObject(Code apiCode) {
        super(apiCode);
    }

    public ResultObject(Code apiCode, String msg) {
        super(apiCode, msg);
    }

    public ResultObject(T t) {
        this.data = t;
    }

    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
