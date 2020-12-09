package com.sys.sys.session;

import com.alibaba.fastjson.JSON;
import com.sys.sys.model.SysUser;

import javax.servlet.http.HttpSession;

/**
 * session 用户
 *
 * @author wfd
 */
public class LoginUtil {

    public static final String SESSION_KEY = "Login__user";
    private static LoginUtil loginutil = new LoginUtil();
    private ThreadLocal<SysUser> threadLocal = new ThreadLocal<SysUser>();

    private LoginUtil() {

    }

    public static LoginUtil getInstance() {
        return loginutil;
    }

    public SysUser get() {
        return threadLocal.get();
    }

    public void set(SysUser user) {
        threadLocal.set(user);
    }

    public SysUser getFormSession(HttpSession session) {
        SysUser sysUser = JSON.parseObject(JSON.toJSONString(session.getAttribute(SESSION_KEY)), SysUser.class);
        return sysUser;
    }

    public void setToSession(HttpSession session, SysUser user) {
        session.setAttribute(SESSION_KEY, user);
    }

    public void clear() {
        threadLocal.remove();
    }
}
