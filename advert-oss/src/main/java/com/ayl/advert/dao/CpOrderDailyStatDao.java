package com.ayl.advert.dao;

import com.ayl.advert.model.CpOrderDailyStat;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CpOrderDailyStatDao {

    public CpOrderDailyStat get(Integer id);

    public CpOrderDailyStat getByOrderDate(@Param("order_id")Integer order_id,@Param("date")String date);

    public void save(CpOrderDailyStat cpOrderDailyStat);

    public int update(CpOrderDailyStat cpOrderDailyStat);

    /**
     * 查询条数
     *
     * @param query 参数
     * @return
     */
    Long findCnt(@Param("query") Map<String, Object> query);

    /**
     * 查询
     *
     * @param query 参数
     * @param start 开始未知
     * @param limit 一页条数
     * @return
     */
    List<Map<String, Object>> find(@Param("query") Map<String, Object> query, @Param("start") Long start,
                                   @Param("limit") Integer limit);

    Long appConsumeDateStatFindCnt(@Param("query")Map<String, Object> query);

    List<Map<String, Object>> appConsumeDateStatFind(@Param("query") Map<String, Object> query, @Param("start") Long start,
                                                     @Param("limit") Integer limit);

    Long appConsumeDateStatDetailFindCnt(@Param("query")Map<String, Object> query);

    List<Map<String, Object>> appConsumeDateStatDetailFind(@Param("query") Map<String, Object> query, @Param("start") Long start,
                                                     @Param("limit") Integer limit);


    Map<String, Object> appConsumeDateStat(@Param("query") Map<String, Object> query);

    int delete(Integer id);
}
