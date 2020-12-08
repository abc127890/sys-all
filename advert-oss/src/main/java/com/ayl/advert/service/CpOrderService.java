package com.ayl.advert.service;

import com.ayl.advert.common.response.Pager;
import com.ayl.advert.model.CpOrder;

import java.util.Map;

public interface CpOrderService {

    public CpOrder get(Integer id);

    public void save(CpOrder cpAppInfo);

    public int update(CpOrder cpAppInfo);

    /**
     * 查询
     *
     * @param query 参数
     * @param page  页
     * @return
     */
    Pager<Map<String, Object>> find(Map<String, Object> query, Pager<Map<String, Object>> page);

    /**
     * 新增完成数量
     * @param id
     * @param cnt
     * @return
     */
    int increaseCompleteCnt(Integer id,Integer cnt);
}
