package com.ayl.advert.model;

import com.alibaba.fastjson.JSON;
import lombok.Data;

import java.util.Date;

@Data
public class CpAppInfo {

    private Integer id;

    private Long app_id;

    private Date create_time;

    @Override
    public String toString(){
        return JSON.toJSONString(this);
    }
}
