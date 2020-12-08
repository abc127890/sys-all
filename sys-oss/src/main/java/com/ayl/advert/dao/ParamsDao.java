package com.ayl.advert.dao;

import com.ayl.advert.model.Params;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author wfd
 */
public interface ParamsDao {

    /**
     * ID查询
     * @param id
     * @return
     */
    Params get(Integer id);

    /**
     * 新增
     * @param params
     */
    void save(Params params);

    /**
     * 修改
     * @param params
     * @return
     */
    int update(Params params);

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
}
