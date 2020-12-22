package com.sys.common.response;

import com.sys.common.response.Result;

import java.util.List;

/**
 * @author wfd
 */
public class ResultList<T> extends Result {

    public ResultList(Code apiCode) {
        super(apiCode);
    }

    public ResultList(Code apiCode, String msg) {
        super(apiCode, msg);
    }

    public ResultList(List<T> t) {
        this.data = t;
    }

    private List<T> data;

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
