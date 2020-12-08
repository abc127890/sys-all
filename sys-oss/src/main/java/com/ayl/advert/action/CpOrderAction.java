package com.ayl.advert.action;

import com.ayl.advert.common.response.Pager;
import com.ayl.advert.common.response.Result;
import com.ayl.advert.common.response.ResultObject;
import com.ayl.advert.model.CpOrder;
import com.ayl.advert.service.CpOrderService;
import com.ayl.advert.sys.model.SysUser;
import com.ayl.advert.sys.service.SysUserService;
import com.ayl.advert.sys.session.LoginUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/cpOrder")
public class CpOrderAction {

    @Autowired
    private CpOrderService cpOrderService;

    @Autowired
    private SysUserService sysUserService;

    @GetMapping(path = "/find")
    public ModelAndView find(ModelAndView mv) {
        Map<String, Object> cpQuery = new HashMap<>(5, 1);
        cpQuery.put("role_id",2);
        mv.addObject("cps", sysUserService.findList(cpQuery));
        mv.setViewName("/cpOrder/find/index");
        return mv;
    }

    @PostMapping(path = "/find")
    public Result find(String start_time, String end_time,Long app_id, Integer pNum, Integer limit) {
        Map<String, Object> query = new HashMap<>(5, 1);
        query.put("app_id", app_id);
        if(StringUtils.isNotBlank(end_time)){
            end_time=end_time+" 23:59:59";
        }
        query.put("start_time", start_time);
        query.put("end_time", end_time);

        SysUser sysUser=LoginUtil.getInstance().get();
        if(sysUser.getRole_id()==2){
            query.put("cp_id", sysUser.getId());
        }

        Pager<Map<String, Object>> pager = cpOrderService.find(query, new Pager<Map<String, Object>>(pNum, limit));
        return new ResultObject<>(pager);
    }

    @GetMapping(path = "/add")
    public ModelAndView add(ModelAndView mv) {
        Map<String, Object> cpQuery = new HashMap<>(5, 1);
        cpQuery.put("role_id",2);
        mv.addObject("cps", sysUserService.findList(cpQuery));
        mv.setViewName("/cpOrder/add/index");
        return mv;
    }

    @PostMapping(path = "/add")
    public Result add(CpOrder cpOrder) {
        cpOrder.setComplete_cnt(0);
        cpOrder.setCreate_time(new Date(System.currentTimeMillis()));
        cpOrderService.save(cpOrder);
        return Result.ok();
    }

    @GetMapping(path = "/update")
    public ModelAndView update(ModelAndView mv, Integer id) {
        Map<String, Object> cpQuery = new HashMap<>(5, 1);
        cpQuery.put("role_id",2);
        mv.addObject("cps", sysUserService.findList(cpQuery));
        mv.addObject("record", cpOrderService.get(id));
        mv.setViewName("/cpOrder/update/index");
        return mv;
    }

    @PostMapping(path = "/update")
    public Result update(CpOrder cpOrder) {
        CpOrder old=cpOrderService.get(cpOrder.getId());
        old.setStart_time(cpOrder.getStart_time());
        old.setEnd_time(cpOrder.getEnd_time());
        old.setCnt(cpOrder.getCnt());
        old.setUnit_price(cpOrder.getUnit_price());
        cpOrderService.update(old);
        return Result.ok();
    }
}
