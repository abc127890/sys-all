package com.sys.sys.right;

import com.alibaba.fastjson.JSON;
import com.sys.common.response.Result;
import com.sys.sys.model.SysUser;
import com.sys.sys.session.LoginUtil;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 后台权限拦截器
 *
 * @author wfd
 */
public class RightInterceptor extends HandlerInterceptorAdapter {

    private LoginUtil loginUtil = LoginUtil.getInstance();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        if (handler == null || !(handler instanceof HandlerMethod)) {
            return true;
        }
        String contextPath = request.getContextPath();
        String path = request.getRequestURI();
        if (contextPath.equals("") || contextPath.equals("/")) {
            path = request.getRequestURI();
        } else {
            path = request.getRequestURI().replaceFirst(contextPath, "");
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        SysUser user = loginUtil.getFormSession(request.getSession());
        loginUtil.set(user);
        if (handlerMethod.getMethod().isAnnotationPresent(NotNeedLogin.class)) {
            return super.preHandle(request, response, handler);
        }


        if (user == null) {
            Result result = new Result(Result.Code.NOT_LOGIN);
            response.getOutputStream().write(JSON.toJSONString(result).getBytes("utf-8"));
            return false;
        }
        if (handlerMethod.getMethod().isAnnotationPresent(NotLimit.class)) {
            return super.preHandle(request, response, handler);
        }

        return super.preHandle(request, response, handler);

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
        loginUtil.clear();
    }

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    public static @interface NotNeedLogin {

    }

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    public static @interface NotLimit {

    }
}
