package com.ayl.advert.model;

import com.alibaba.fastjson.JSON;
import lombok.Data;

import java.util.Date;

/**
 * @author wfd
 */
@Data
public class Params {

    /**
     * ID
     */
    private Integer id;

    /**
     * 值
     */
    private String value;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    private Date create_time;

    @Override
    public String toString(){
        return JSON.toJSONString(this);
    }
}
