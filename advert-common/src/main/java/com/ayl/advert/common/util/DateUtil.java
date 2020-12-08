package com.ayl.advert.common.util;

import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;


public class DateUtil {

    public static final String UTC="yyyy-MM-dd'T'HH:mm:ssZ";

    public static Date parse(String datestr) {
        if (StringUtils.isBlank(datestr)) {
            return null;
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return format.parse(datestr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Date parse(String datestr, String fmt) {
        if (StringUtils.isBlank(datestr)) {
            return null;
        }
        SimpleDateFormat format = new SimpleDateFormat(fmt);
        try {
            return format.parse(datestr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Date parseYYYYMM(String datestr) {
        if (StringUtils.isBlank(datestr)) {
            return null;
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
        try {
            return format.parse(datestr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Date parseYYYYMMDD(String datestr) {
        if (StringUtils.isBlank(datestr)) {
            return null;
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return format.parse(datestr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Date parseYYYYMMDDHHMM(String datestr) {
        if (StringUtils.isBlank(datestr)) {
            return null;
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
            return format.parse(datestr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Date parseYYYYMMDDHHMMSS(String datestr) {
        if (StringUtils.isBlank(datestr)) {
            return null;
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return format.parse(datestr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String formatYYYYMMDD(long time) {


        return formatYYYYMMDD(new Date(time));

    }

    public static String formatYYYYMMDD(Date date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        return format.format(date);

    }

    public static String formatYYYYMM(long time) {


        return formatYYYYMMDD(new Date(time));

    }

    public static String formatYYYYMM(Date date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");

        return format.format(date);

    }

    public static String format(Long time) {
        if (time == null) {
            return null;
        }

        return format(new Date(time));

    }

    public static String format(long time, String pattern) {


        return format(new Date(time), pattern);

    }

    public static String format(Date date) {

        return format(date, "yyyy-MM-dd HH:mm:ss");

    }

    public static String format(Date date, String pattern) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat format = new SimpleDateFormat(pattern);

        return format.format(date);

    }


    public static String format(Date date,String timeZone, String pattern) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        format.setTimeZone(TimeZone.getTimeZone(ZoneId.of(timeZone)));
        return format.format(date);

    }


    public static Date getMinMonthDate() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_MONTH, c.getActualMinimum(Calendar.DAY_OF_MONTH));
        return c.getTime();
    }


    public static Date getMaxMonthDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return calendar.getTime();
    }

    public static Date getMinMonthDate(Date time) {
        Calendar c = Calendar.getInstance();
        c.setTime(time);
        c.set(Calendar.DAY_OF_MONTH, c.getActualMinimum(Calendar.DAY_OF_MONTH));
        return c.getTime();
    }


    public static Date getMaxMonthDate(Date time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return calendar.getTime();
    }

    public static Date getThisWeekMonday(Date date) {
        date = DateUtil.parseYYYYMMDD(DateUtil.formatYYYYMMDD(date));
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);
        if (1 == dayWeek) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        int day = cal.get(Calendar.DAY_OF_WEEK);
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);
        return cal.getTime();
    }


    public static int getBetweenDay(Date start, Date end) {
        Calendar fromCalendar = Calendar.getInstance();
        fromCalendar.setTime(start);
        fromCalendar.set(Calendar.HOUR_OF_DAY, 0);
        fromCalendar.set(Calendar.MINUTE, 0);
        fromCalendar.set(Calendar.SECOND, 0);
        fromCalendar.set(Calendar.MILLISECOND, 0);

        Calendar toCalendar = Calendar.getInstance();
        toCalendar.setTime(end);
        toCalendar.set(Calendar.HOUR_OF_DAY, 0);
        toCalendar.set(Calendar.MINUTE, 0);
        toCalendar.set(Calendar.SECOND, 0);
        toCalendar.set(Calendar.MILLISECOND, 0);

        return (int) ((toCalendar.getTime().getTime() - fromCalendar.getTime().getTime()) / (1000 * 60 * 60 * 24));
    }


    /**
     * 把传入的时间设置为起始时间,把时分秒设置为0,yyyy-mm-dd 00:00:00
     *
     * @return
     */
    public static Date getBeginDate(Date current) {
        Calendar c = Calendar.getInstance();//获取Calendar的实例化对象
        c.setTime(current);
        //c.set(c.get(Calendar.YEAR),c.get(Calendar.MONTH),c.get(Calendar.DATE),0,0,0);   //2016-8-28 0:00:00
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND,0);

        return c.getTime();
    }

    /**
     * 把传入的时间设置为结束时间,把时分秒设置为yyyy-mm-dd 23:59:59
     *
     * @return
     */
    public static Date getEndDate(Date current) {
        Calendar c = Calendar.getInstance();
        c.setTime(current);
        c.set(Calendar.HOUR_OF_DAY, 0); //把时分秒设置为0,0,0
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND,0);
        c.add(Calendar.DATE, 1);
        c.add(Calendar.SECOND, -1);
        return c.getTime();
    }

    /**
     * 前几天或者后几天的开始时间
     *
     * @param day
     * @return
     */
    public static long nextDayORafterDayBeginTime(Date date, int day) {
        Calendar calendar = Calendar.getInstance();
        if (date != null) {
            calendar.setTime(date);
        }
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.add(Calendar.DATE, day);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTime().getTime();
    }

    /**
     * 前几天或者后几天的结束时间
     *
     * @param day
     * @return
     */
    public static long nextDayORafterDayEndTime(Date date, int day) {
        Calendar calendar = Calendar.getInstance();
        if (date != null) {
            calendar.setTime(date);
        }
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        calendar.add(Calendar.DATE, day);

        return calendar.getTime().getTime();
    }

    public static void main(String[] args) {
        System.out.println(DateUtil.nextDayORafterDayBeginTime(null, -7));
//        System.out.println(parseYYYYMMDDHHMM("2018-11-05 00:00").getTime());
//        System.out.println(parseYYYYMMDDHHMM("2018-11-05 23:59").getTime() + 59 * 1000);
//        System.out.println(System.currentTimeMillis());
//
//        System.out.println(getBetweenDay(parseYYYYMMDDHHMM("2018-08-06 00:00"), parseYYYYMMDDHHMM("2018-08-07 00:00")));
//
//        System.out.println(System.currentTimeMillis());


//		File[] files=new File("C:\\Users\\Helloworld\\Desktop\\新建文件夹 (2)").listFiles();
//		int total=0;
//		for(File file:files) {
//			int cnt=Integer.parseInt(file.getName().split("[-]")[5].replace(".xls", ""));
//			total+=cnt;
//		}
//
//		System.out.println(total);
    }
}
