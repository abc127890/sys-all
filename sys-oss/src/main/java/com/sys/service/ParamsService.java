package com.sys.service;

import com.sys.common.response.Pager;
import com.sys.model.Params;

import java.util.Map;

/**
 * @author wfd
 */
public interface ParamsService {

    /**
     * ID查询
     *
     * @param id
     * @return
     */
    Params get(Integer id);

    /**
     * 新增
     *
     * @param params
     */
    void save(Params params);

    /**
     * 修改
     *
     * @param params
     * @return
     */
    int update(Params params);

    /**
     * 查询
     *
     * @param query 参数
     * @param page  页
     * @return
     */
    Pager<Map<String, Object>> find(Map<String, Object> query, Pager<Map<String, Object>> page);
}
