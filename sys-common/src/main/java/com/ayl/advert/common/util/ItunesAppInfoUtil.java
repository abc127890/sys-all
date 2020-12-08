package com.ayl.advert.common.util;

import com.alibaba.fastjson.JSON;
import com.ayl.advert.common.response.BusinessException;
import com.ayl.advert.common.response.Result;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;

/**
 * 获取苹果应用信息
 *
 * @author wfd
 */
public class ItunesAppInfoUtil {

    private static Logger logger = LoggerFactory.getLogger(ItunesAppInfoUtil.class.getName());

    private static String url = "https://itunes.apple.com/lookup?id=";

    private static String url2 = "https://itunes.apple.com/cn/lookup?id=";

    private static String url3 = "http://rank.noenc.com/appinfo/getappinfo.php?id=";

    public static ItunesAppInfo.Info get(String app_id) {
        try {
            return get(url2, app_id);
        } catch (BusinessException e) {
            if (Result.Code.NOT_FOUND.getCode().equals(e.getCode())) {
                return get(url, app_id);
            }
            throw e;
        } catch (Exception e) {
            return get(url3, app_id);
        }
    }

    public static ItunesAppInfo.Info get(String url, String app_id) {
        url = url + app_id + "&t=" + System.currentTimeMillis();
        HttpURLConnection conn = null;
        InputStream inputStream = null;
        try {
            conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(8000);
            conn.setReadTimeout(8000);
            conn.setDoOutput(true);
            if (logger.isDebugEnabled()) {
                logger.debug("获取苹果应用信息请求：" + url);
            }
            int responseCode = conn.getResponseCode();
            if (conn.getResponseCode() == 200) {
                inputStream = conn.getInputStream();
                String returnStr = IOUtils.toString(inputStream, "UTF-8").trim();
                logger.debug("returnStr：" + returnStr);
                ItunesAppInfo itunesAppInfo = JSON.parseObject(returnStr, ItunesAppInfo.class);
                if (itunesAppInfo.getResultCount() == 0) {
                    logger.error("未找到苹果ID：" + app_id + "应用信息");
                    throw new BusinessException(Result.Code.NOT_FOUND, "未找到苹果ID：" + app_id + "应用信息");
                }
                ItunesAppInfo.Info info = itunesAppInfo.getResults().get(0);
                info.setTrackViewUrl(URLDecoder.decode(info.getTrackViewUrl(), "UTF-8"));
                return info;
            } else {
                logger.error("获取苹果应用信息响应码:" + responseCode);
                throw new RuntimeException("获取苹果应用信息异常");
            }
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            logger.error("获取苹果应用信息异常", e);
            throw new RuntimeException("获取苹果应用信息异常", e);
        } finally {
            IOUtils.closeQuietly(inputStream);
            IOUtils.close(conn);
        }
    }


    public static void main(String[] args) {
        ItunesAppInfo.Info info = ItunesAppInfoUtil.get("1484939743");
        System.out.println(info.getTrackName());
        System.out.println(info.getAppName());
        System.out.println(info.getPrice());
    }
}
