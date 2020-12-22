package com.sys.sys.action;


import com.sys.common.response.Result;
import com.sys.common.response.ResultObject;
import com.sys.sys.model.SysMenu;
import com.sys.sys.model.SysUser;
import com.sys.sys.right.RightInterceptor;
import com.sys.sys.service.SysUserService;
import com.sys.sys.session.LoginUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 首页入口
 *
 * @author wfd
 */
@RestController
public class IndexAction {

    @Autowired
    private SysUserService sysUserService;

    @RightInterceptor.NotNeedLogin
    @GetMapping("/")
    public ModelAndView home(HttpServletRequest request, ModelAndView mv) {
        if (LoginUtil.getInstance().getFormSession(request.getSession()) != null) {
            mv.setViewName("/sys/home/home");
        } else {
            mv.setViewName("/sys/login");
        }
        return mv;
    }

    @RightInterceptor.NotNeedLogin
    @ResponseBody
    @PostMapping("/sys/login")
    public Result login(HttpServletRequest request, String user_name, String password) {

        SysUser sysUser = sysUserService.getByUserName(user_name);
        if (sysUser == null) {
            return new Result(Result.Code.SYSTEM_ERROR, "用户不存在");
        }

        if (!sysUser.getPassword().equals(DigestUtils.md5Hex(password))) {
            return new Result(Result.Code.SYSTEM_ERROR, "密码输入错误");
        }

        LoginUtil.getInstance().setToSession(request.getSession(), sysUser);

        SysMenu root = new SysMenu();
        root.setId("1");
        root.setName("参数管理");
        root.setExpand((byte) 1);
        root.setLeaf((byte) 0);
        root.setLevel((byte) 1);
        root.setRank((byte) 1);
        List<SysMenu> childrens = new ArrayList<>();

        {
            SysMenu order = new SysMenu();
            order.setId("1-1");
            order.setName("参数配置");
            order.setExpand((byte) 0);
            order.setLeaf((byte) 1);
            order.setLevel((byte) 2);
            order.setRank((byte) 1);
            order.setUrl("/params/find");
            childrens.add(order);
        }

        root.setChildrens(childrens);

        List<SysMenu> roots = new ArrayList<>();
        roots.add(root);
        request.getSession().setAttribute("menus", roots);

        return new ResultObject<>();
    }

    @ResponseBody
    @PostMapping("/sys/logout")
    public Result logout(HttpServletRequest request) {
        request.getSession().removeAttribute(LoginUtil.SESSION_KEY);
        return new Result();
    }


    @ResponseBody
    @GetMapping("/sysUser/updatePassword")
    public ModelAndView updatePassword(HttpServletRequest request, ModelAndView mv) {
        mv.setViewName("/sys/updatePassword");
        return mv;
    }

    @ResponseBody
    @PostMapping("/sysUser/updatePassword")
    public Result updatePassword(String password, String new_password) {
        SysUser sysUser = LoginUtil.getInstance().get();
        sysUser = sysUserService.get(sysUser.getId());
        if (!sysUser.getPassword().equals(DigestUtils.md5Hex(password))) {
            return new Result(Result.Code.SYSTEM_ERROR, "原密码输入错误");
        }
        sysUserService.updatePassword(sysUser.getId(), DigestUtils.md5Hex(new_password));
        return new Result();
    }


    @ResponseBody
    @GetMapping("/my/menus")
    public Result myMenus(HttpServletRequest request, ModelAndView mv) {
        return new ResultObject<>(request.getSession().getAttribute("menus"));
    }
}
