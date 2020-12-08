package com.ayl.advert.model;


import com.alibaba.fastjson.JSON;
import lombok.Data;

import java.util.Date;

@Data
public class AppInfo {

    private Long app_id;

    private String name;

    private String icon;

    private Integer free;

    private Double price;

    private String itunes_url;

    private String bundle_id;

    private Long file_size;

    private String size_name;

    private Date update_time;

    @Override
    public String toString(){
        return JSON.toJSONString(this);
    }
}
