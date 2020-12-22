package com.sys.common.util;

import org.apache.poi.ss.usermodel.Workbook;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class ExcelUtil {

    public static void setFileDownloadHeader(HttpServletResponse response, String filename) {
        String headerValue = "attachment;";
        headerValue += " filename=\"" + encodeURIComponent(filename) + "\";";
        headerValue += " filename*=utf-8''" + encodeURIComponent(filename);
        response.setHeader("Content-Disposition", headerValue);
    }

    /**
     * <pre>
     * 符合 RFC 3986 标准的“百分号URL编码”
     * 在这个方法里，空格会被编码成%20，而不是+
     * 和浏览器的encodeURIComponent行为一致
     * </pre>
     *
     * @param value
     * @return
     */
    public static String encodeURIComponent(String value) {
        try {
            return URLEncoder.encode(value, "UTF-8").replaceAll("\\+", "%20");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    //浏览器下载excel
    public static void write(String filename, Workbook workbook, HttpServletResponse response) throws IOException {

        response.setContentType("application/vnd.ms-excel");
        setFileDownloadHeader(response, filename);
        write(workbook, response.getOutputStream());
    }

    public static void write(Workbook workbook, ServletOutputStream outputStream) throws IOException {
        workbook.write(outputStream);
        outputStream.flush();
    }
}
