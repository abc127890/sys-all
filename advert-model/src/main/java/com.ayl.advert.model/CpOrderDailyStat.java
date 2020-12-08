package com.ayl.advert.model;

import com.alibaba.fastjson.JSON;
import lombok.Data;

import java.util.Date;

@Data
public class CpOrderDailyStat {

    private Integer  id;

    private Integer order_id;

    private String date;

    private Integer complete_cnt;

    private Date create_time;

    @Override
    public String toString(){
        return JSON.toJSONString(this);
    }
}
