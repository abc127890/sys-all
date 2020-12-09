package com.sys.sys.dao;


import com.sys.sys.model.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SysUserDao {

    public SysUser get(Integer id);

    public SysUser getByUserName(String user_name);

    public void updatePassword(@Param("id") Integer id, @Param("password") String password);

    List<SysUser> findList(@Param("query") Map<String, Object> query);

}
