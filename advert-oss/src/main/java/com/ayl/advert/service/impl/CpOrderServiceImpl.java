package com.ayl.advert.service.impl;

import com.ayl.advert.common.response.Pager;
import com.ayl.advert.dao.CpOrderDao;
import com.ayl.advert.model.CpOrder;
import com.ayl.advert.service.AppInfoService;
import com.ayl.advert.service.CpOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
public class CpOrderServiceImpl implements CpOrderService {

    @Autowired
    private CpOrderDao cpOrderDao;

    @Autowired
    private AppInfoService appInfoService;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public CpOrder get(Integer id) {
        return cpOrderDao.get(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void save(CpOrder cpOrder) {
        appInfoService.getAndUpdateFromStore(cpOrder.getApp_id());
        cpOrderDao.save(cpOrder);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public int update(CpOrder cpOrder) {
        appInfoService.getAndUpdateFromStore(cpOrder.getApp_id());
        return cpOrderDao.update(cpOrder);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Pager<Map<String, Object>> find(Map<String, Object> query, Pager<Map<String, Object>> page) {
        page.setTotal(cpOrderDao.findCnt(query));
        page.setList(cpOrderDao.find(query, page.getStart(), page.getLimit()));
        return page;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public int increaseCompleteCnt(Integer id, Integer cnt) {
        return cpOrderDao.increaseCompleteCnt(id, cnt);
    }


}
