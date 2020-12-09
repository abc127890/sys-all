package com.sys.sys.service.impl;

import com.sys.sys.dao.SysUserDao;
import com.sys.sys.model.SysUser;
import com.sys.sys.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserDao sysUserDao;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public SysUser get(Integer id) {
        return sysUserDao.get(id);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public SysUser getByUserName(String user_name) {
        return sysUserDao.getByUserName(user_name);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void updatePassword(Integer id, String password) {
        sysUserDao.updatePassword(id, password);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<SysUser> findList(Map<String, Object> query) {
        return sysUserDao.findList(query);
    }
}
