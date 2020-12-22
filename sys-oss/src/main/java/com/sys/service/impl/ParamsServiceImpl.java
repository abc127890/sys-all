package com.sys.service.impl;

import com.sys.common.response.Pager;
import com.sys.dao.ParamsDao;
import com.sys.model.Params;
import com.sys.service.ParamsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * @author wfd
 */
@Service
@Transactional
public class ParamsServiceImpl implements ParamsService {

    @Autowired
    private ParamsDao paramsDao;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Params get(Integer id) {
        return paramsDao.get(id);
    }

    @Override
    public void save(Params params) {
        paramsDao.save(params);
    }

    @Override
    public int update(Params params) {
        return paramsDao.update(params);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Pager<Map<String, Object>> find(Map<String, Object> query, Pager<Map<String, Object>> page) {
        page.setTotal(paramsDao.findCnt(query));
        page.setList(paramsDao.find(query, page.getStart(), page.getLimit()));
        return page;
    }
}
