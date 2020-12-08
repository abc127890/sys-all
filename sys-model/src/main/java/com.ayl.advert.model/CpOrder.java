package com.ayl.advert.model;

import com.alibaba.fastjson.JSON;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class CpOrder {

    private Integer  id;

    private Integer cp_id;

    private Long app_id;

    private String keyword;

    private Date start_time;

    private Date end_time;

    private BigDecimal unit_price;

    private Integer cnt;

    private Integer complete_cnt;

    private Date create_time;

    @Override
    public String toString(){
        return JSON.toJSONString(this);
    }
}
