package com.ayl.advert.sys.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Date;

/**
 * 系统用户
 * @author wfd
 */
@Data
public class SysUser {

    private Integer id;

    private String user_name;

    private Integer role_id;

    private String real_name;

    @JsonIgnore
    @JSONField(serialize = false)
    private String password;

    private Date create_time;

}
