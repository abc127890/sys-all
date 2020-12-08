package com.ayl.advert.dao;

import com.ayl.advert.model.CpOrder;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CpOrderDao {

    public CpOrder get(Integer id);

    public void save(CpOrder cpAppInfo);

    public int update(CpOrder cpAppInfo);

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

    /**
     * 新增完成数量
     * @param id
     * @param cnt
     * @return
     */
    int increaseCompleteCnt(@Param("id") Integer id,@Param("cnt") Integer cnt);
}
