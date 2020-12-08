package com.ayl.advert.service;

import com.ayl.advert.common.response.Pager;
import com.ayl.advert.model.CpAppInfo;

import java.util.Map;

public interface CpAppInfoService {

    public CpAppInfo get(Integer id);

    public void save(CpAppInfo cpAppInfo);

    /**
     * 查询
     *
     * @param query 参数
     * @param page  页
     * @return
     */
    Pager<Map<String, Object>> find(Map<String, Object> query, Pager<Map<String, Object>> page);
}
