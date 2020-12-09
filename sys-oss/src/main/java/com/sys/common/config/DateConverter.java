package com.sys.common.config;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * spring mvc
 * 日期属性解析器
 *
 * @author wfd
 */
public class DateConverter implements Converter<String, Date> {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public Date convert(String source) {

        if (StringUtils.isBlank(source))
            return null;
        source = source.trim();
        if (StringUtils.isNumeric(source))
            return new Date(Long.parseLong(source));
        SimpleDateFormat dateFormat;
        switch (source.length()) {
            case 8:
                dateFormat = new SimpleDateFormat("HH:mm:ss");
                break;
            case 10:
                dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                break;
            case 4:
                dateFormat = new SimpleDateFormat("yyyy");
                break;
            case 24:
                dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
                break;
            default:
                dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
        try {
            Date date = dateFormat.parse(source);
            if (source.length() == 24) {
                date.setTime(date.getTime() + 8 * 60 * 60 * 1000);
            }
            return date;
        } catch (ParseException e) {
            log.error("日期转换异常", e);
            throw new RuntimeException(e);
        }
    }
}
