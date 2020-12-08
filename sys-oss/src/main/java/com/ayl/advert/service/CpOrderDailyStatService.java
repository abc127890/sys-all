package com.ayl.advert.service;

import com.ayl.advert.common.response.Pager;
import com.ayl.advert.model.CpOrderDailyStat;

import java.util.List;
import java.util.Map;

public interface CpOrderDailyStatService {

    public CpOrderDailyStat get(Integer id);

    public CpOrderDailyStat getByOrderDate(Integer order_id,String date);

    public void save(CpOrderDailyStat cpOrderDailyStat);

    public int update(CpOrderDailyStat cpOrderDailyStat);

    /**
     * 查询
     *
     * @param query 参数
     * @param page  页
     * @return
     */
    Pager<Map<String, Object>> find(Map<String, Object> query, Pager<Map<String, Object>> page);

    Map<String, Object> appConsumeDateStat(Map<String, Object> query);

    Pager<Map<String, Object>> appConsumeDateStatFind(Map<String, Object> query, Pager<Map<String, Object>> page);

    List<Map<String, Object>> appConsumeDateStatFind(Map<String, Object> query);

    Pager<Map<String, Object>> appConsumeDateStatDetailFind(Map<String, Object> query, Pager<Map<String, Object>> page);

    List<Map<String, Object>> appConsumeDateStatDetailFind(Map<String, Object> query);

    int delete(Integer id);

    void saveOrUpdate(CpOrderDailyStat cpOrderDailyStat);
}
