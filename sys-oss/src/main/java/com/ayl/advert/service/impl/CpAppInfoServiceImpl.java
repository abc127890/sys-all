package com.ayl.advert.service.impl;

import com.ayl.advert.common.response.Pager;
import com.ayl.advert.dao.CpAppInfoDao;
import com.ayl.advert.model.CpAppInfo;
import com.ayl.advert.service.CpAppInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
public class CpAppInfoServiceImpl implements CpAppInfoService {

    @Autowired
    private CpAppInfoDao cpAppInfoDao;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public CpAppInfo get(Integer id) {
        return cpAppInfoDao.get(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void save(CpAppInfo cpAppInfo) {
        cpAppInfoDao.save(cpAppInfo);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Pager<Map<String, Object>> find(Map<String, Object> query, Pager<Map<String, Object>> page) {
        page.setTotal(cpAppInfoDao.findCnt(query));
        page.setList(cpAppInfoDao.find(query, page.getStart(), page.getLimit()));
        return page;
    }
}
