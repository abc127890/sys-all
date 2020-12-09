package com.sys.service.impl;

import com.ayl.advert.common.response.Pager;
import com.ayl.advert.model.CpOrderDailyStat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class CpOrderDailyStatServiceImpl implements CpOrderDailyStatService {

    @Autowired
    private CpOrderDailyStatDao cpOrderDailyStatDao;

    @Autowired
    private CpOrderService cpOrderService;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public CpOrderDailyStat get(Integer id) {
        return cpOrderDailyStatDao.get(id);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public CpOrderDailyStat getByOrderDate(Integer order_id, String date) {
        return cpOrderDailyStatDao.getByOrderDate(order_id, date);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void save(CpOrderDailyStat cpOrderDailyStat) {
        cpOrderDailyStatDao.save(cpOrderDailyStat);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public int update(CpOrderDailyStat cpOrderDailyStat) {
        return cpOrderDailyStatDao.update(cpOrderDailyStat);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Pager<Map<String, Object>> find(Map<String, Object> query, Pager<Map<String, Object>> page) {
        page.setTotal(cpOrderDailyStatDao.findCnt(query));
        page.setList(cpOrderDailyStatDao.find(query, page.getStart(), page.getLimit()));
        return page;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Map<String, Object> appConsumeDateStat(Map<String, Object> query) {
        return cpOrderDailyStatDao.appConsumeDateStat(query);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Pager<Map<String, Object>> appConsumeDateStatFind(Map<String, Object> query, Pager<Map<String, Object>> page) {
        page.setTotal(cpOrderDailyStatDao.appConsumeDateStatFindCnt(query));
        page.setList(cpOrderDailyStatDao.appConsumeDateStatFind(query, page.getStart(), page.getLimit()));
        return page;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Map<String, Object>> appConsumeDateStatFind(Map<String, Object> query) {
        return cpOrderDailyStatDao.appConsumeDateStatFind(query, null, null);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Pager<Map<String, Object>> appConsumeDateStatDetailFind(Map<String, Object> query, Pager<Map<String, Object>> page) {
        page.setTotal(cpOrderDailyStatDao.appConsumeDateStatDetailFindCnt(query));
        page.setList(cpOrderDailyStatDao.appConsumeDateStatDetailFind(query, page.getStart(), page.getLimit()));
        return page;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Map<String, Object>> appConsumeDateStatDetailFind(Map<String, Object> query) {
        return cpOrderDailyStatDao.appConsumeDateStatDetailFind(query, null, null);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveOrUpdate(CpOrderDailyStat cpOrderDailyStat) {
        CpOrderDailyStat old = this.getByOrderDate(cpOrderDailyStat.getOrder_id(), cpOrderDailyStat.getDate());
        int add_cnt = 0;
        if (old != null) {
            add_cnt = cpOrderDailyStat.getComplete_cnt() - old.getComplete_cnt();
            old.setComplete_cnt(cpOrderDailyStat.getComplete_cnt());
            this.update(old);

        } else {
            add_cnt = cpOrderDailyStat.getComplete_cnt();
            cpOrderDailyStat.setCreate_time(new Date());
            this.save(cpOrderDailyStat);
        }
        this.cpOrderService.increaseCompleteCnt(cpOrderDailyStat.getOrder_id(), add_cnt);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public int delete(Integer id) {
        CpOrderDailyStat cpOrderDailyStat = this.get(id);
        this.cpOrderService.increaseCompleteCnt(cpOrderDailyStat.getOrder_id(), cpOrderDailyStat.getComplete_cnt() * -1);
        return cpOrderDailyStatDao.delete(id);
    }
}
