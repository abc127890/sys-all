package com.sys.common.util;

import java.text.DecimalFormat;

/**
 * 数字格式化工具
 *
 * @author wfd
 */
public class NumberFormat {

    private static java.text.NumberFormat mnf = new DecimalFormat("#,###.###");

    private static java.text.NumberFormat mnf1 = new DecimalFormat("#.#");

    private static java.text.NumberFormat mnf2 = new DecimalFormat("#.##");

    private static java.text.NumberFormat mnf3 = new DecimalFormat("#.###");

    /**
     * 显示人民币
     *
     * @param v
     * @return
     */
    public static String showMoney(Object v) {
        return mnf.format(v);
    }

    /**
     * 保留小数点后一位
     *
     * @return
     */
    public static String fixed1(Object v) {
        return mnf1.format(v);
    }

    /**
     * 保留小数点后两位
     *
     * @return
     */
    public static String fixed2(Object v) {
        return mnf2.format(v);
    }

    /**
     * 保留小数点后两位
     *
     * @return
     */
    public static String fixed3(Object v) {
        return mnf3.format(v);
    }

    public static void main(String[] args) {
        System.out.println(fixed3(340.234234));
    }

}
