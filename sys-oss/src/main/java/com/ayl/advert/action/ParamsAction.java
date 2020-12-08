package com.ayl.advert.action;

import com.ayl.advert.common.response.Pager;
import com.ayl.advert.common.response.Result;
import com.ayl.advert.common.response.ResultObject;
import com.ayl.advert.model.Params;
import com.ayl.advert.service.ParamsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/params")
public class ParamsAction {

    @Autowired
    private ParamsService paramsService;

    @GetMapping(path = "/find")
    public ModelAndView find(ModelAndView mv) {
        mv.setViewName("/params/find/index");
        return mv;
    }

    @PostMapping(path = "/find")
    public Result find(Integer id, String remark, Integer pNum, Integer limit) {
        Map<String, Object> query = new HashMap<>(5, 1);
        query.put("id", id);
        query.put("remark", remark);
        Pager<Map<String, Object>> pager = paramsService.find(query, new Pager<Map<String, Object>>(pNum, limit));
        return new ResultObject<>(pager);
    }


    @GetMapping(path = "/add")
    public ModelAndView add(ModelAndView mv) {
        mv.setViewName("/params/add/index");
        return mv;
    }

    @PostMapping(path = "/add")
    public Result add(Params params) {
        if (paramsService.get(params.getId()) != null) {
            throw new IllegalArgumentException("ID重复");
        }
        params.setCreate_time(new Date(System.currentTimeMillis()));
        paramsService.save(params);
        return Result.ok();
    }

    @GetMapping(path = "/update")
    public ModelAndView update(ModelAndView mv, Integer id) {
        mv.addObject("record", paramsService.get(id));
        mv.setViewName("/params/update/index");
        return mv;
    }

    @PostMapping(path = "/update")
    public Result update(Params params) {
        paramsService.update(params);
        return Result.ok();
    }

    @DeleteMapping(path = "/delete")
    public Result delete(Integer id) {
        return Result.error(Result.Code.NO_RIGHT);
    }

    @DeleteMapping(path = "/deletes")
    public Result deletes(@RequestParam("ids[]") Integer[] ids) {
        return Result.error(Result.Code.NO_RIGHT);
    }
}
