package com.sys.sys.service;

import com.sys.sys.model.SysUser;

import java.util.List;
import java.util.Map;

public interface SysUserService {

    public SysUser get(Integer id);

    public SysUser getByUserName(String user_name);

    public void updatePassword(Integer id, String password);

    List<SysUser> findList(Map<String, Object> query);
}
