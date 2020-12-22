package com.sys.common.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * 分页
 *
 * @param <T>
 * @author wfd
 */
@Data
@ToString
public class Pager<T> {

    private int limit = 15;

    private int pNum = 1;

    private long total = 0;

    private long pCnt = 0;

    private List<T> list;

    public Pager() {

    }

    public Pager(Pager pager) {
        init(pager.getPNum(), pager.getLimit(), pager.getTotal());
        list = new ArrayList<>(pager.getList().size());
    }

    public Pager(Integer pNum, Integer limit) {
        init(pNum, limit, null);
    }

    private void init(Integer pNum, Integer limit, Long total) {
        if (total != null && total > 0) {
            this.total = total;
        }
        if (pNum != null && pNum > 0) {
            this.pNum = pNum;
        }
        if (limit != null && limit > 0) {
            this.limit = limit;
        }
    }

    @JsonIgnore
    public long getStart() {
        return (this.getPNum() - 1) * this.getLimit();
    }

    @JsonProperty("pNum")
    public int getPNum() {
        return this.pNum;
    }

    public void setPNum(int pNum) {
        if (pNum <= 0) {
            return;
        }
        this.pNum = pNum;
    }

    public int getLimit() {
        return this.limit;
    }

    public void setLimit(int limit) {
        if (limit <= 0) {
            return;
        }
        this.limit = limit;
    }

    public void setTotal(long total) {
        if (total <= 0) {
            return;
        }
        this.total = total;
    }

    public long getTotal() {
        return total;
    }

    @JsonProperty("pCnt")
    public long getPCnt() {

        if (total == 0) {
            pCnt = 0;
        } else if (total % this.limit == 0) {
            return pCnt = total / this.limit;
        } else {
            return pCnt = total / this.limit + 1;
        }
        return pCnt;
    }

    public List<T> getList() {
        return this.list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

}
