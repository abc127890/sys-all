package com.ayl.advert.sys.session;

import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSON;
import com.ayl.advert.sys.model.SysUser;

/**
 * session 用户
 *
 * @author wfd
 */
public class LoginUtil {

    private static LoginUtil loginutil = new LoginUtil();

    private LoginUtil() {

    }

    public static LoginUtil getInstance() {
        return loginutil;
    }

    public static final String SESSION_KEY = "Login__user";

    private ThreadLocal<SysUser> threadLocal = new ThreadLocal<SysUser>();

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
