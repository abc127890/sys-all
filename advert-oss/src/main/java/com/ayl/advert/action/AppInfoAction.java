package com.ayl.advert.action;

import com.ayl.advert.common.response.Result;
import com.ayl.advert.common.response.ResultObject;

import com.ayl.advert.service.AppInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app/info")
public class AppInfoAction {

    @Autowired
    private AppInfoService appInfoService;

    @PostMapping(path = "/")
    public Result add(Long app_id) {
        return new ResultObject<>(appInfoService.getAndUpdateFromStore(app_id));
    }


}
