package com.ahyq.utilslibrary;

import android.text.TextUtils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by 冯鑫 on 2017/12/4.
 */

public class DateUtil {
    public static final String DATE_NORMAL_FORMAT = "yyyy-MM-dd";
    public static final String DATETIME_NORMAL_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_COMPACT_FORMAT = "yyyyMMdd";
    public static final String DATETIME_COMPACT_FORMAT = "yyyyMMddHHmmss";
    public static final String YM_NORMAL_FORMAT = "yyyy-MM";
    public static final String YM_COMPACT_FORMAT = "yyyyMM";
    public static final String DATE_FORMAT_1 = "yyyy-MM-dd'T'HH:mm:ssZ";
    public static final String DATE_FORMAT_2 = "yyyy.M.d";
    public static final String DATE_FORMAT_3 = "yyyy-MM-dd HH:mm";
    public static final String DATE_FORMAT_4 = "HH:mm";
    public static final String DATE_FORMAT_5 = "MM-dd HH:mm";
    public static final String DATE_FORMAT_6 = "HH:MM:SS";

    public static final String DATE_FORMAT_7 = "MM-dd";
    public static final String DATE_FORMAT_8 = "yy-MM-dd HH:mm";

    public static final String DATE_FORMAT_9 = "mm:ss";
    public static final String DATE_FORMAT_10 = "MM-dd HH:mm:ss";

    public static final String DATE_FORMAT_11 = "MM-dd HH";
    public static final String DATE_FORMAT_12 = "HH:mm:ss";
    /**
     * @param timeMillis 时间戳字符串，
     * @param format     想要的格式时间，如可以传入DateUtil.DATE_FORMAT_3;
     * @return 返回你想要的时间格式，如果传入的时间戳 timeMillis 是空，则返回当前。
     */

    public static String formatDate(String timeMillis, String format) {
        Date date = null;
        if (!TextUtils.isEmpty(timeMillis)) {
            try {
                date = new Date(Long.parseLong(timeMillis));
            } catch (NumberFormatException e) {
                e.printStackTrace();
                date = new Date();
            }
        } else {
            date = new Date();
        }

        final SimpleDateFormat formatter = new SimpleDateFormat(format);
        return formatter.format(date);
    }

    /**
     * @param timeMillis 时间戳，
     * @param format     想要的格式时间，如可以传入DateUtil.DATE_FORMAT_3;
     * @return 返回你想要的时间格式，如果传入的时间戳 timeMillis 是空，则返回当前。
     */
    public static String formatDate(long timeMillis, String format) {
        Date date = null;
        if (timeMillis > 0) {
            date = new Date(timeMillis);
        } else {
            date = new Date();
        }
        final SimpleDateFormat formatter = new SimpleDateFormat(format);
        return formatter.format(date);
    }

    /**
     * @param timeMillis 时间戳，
     * @return Date timeMillis 是空，则返回当前。
     */
    public static Date getDate(long timeMillis) {
        Date date = null;
        if (timeMillis > 0) {
            date = new Date(timeMillis);
        } else {
            date = new Date();
        }
        return date;
    }

    /**
     * String转Timestamp
     *
     * @param dateStr
     */
    public static Timestamp stringToTimestamp(String dateStr) {
        try {
            if (dateStr.length() <= 10) {
                dateStr += " 00:00:00";
            }
            return Timestamp.valueOf(dateStr);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * String转Timestamp
     * @param dateStr
     * @return
     */
    public static long stringToTimestamp(String dateStr, String format) {
        if (dateStr == null || "".equals(dateStr)) {
            return System.currentTimeMillis();
        }
        Date date = null;
        //注意format的格式要与日期String的格式相匹配
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            date = sdf.parse(dateStr);
            return date.getTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return System.currentTimeMillis();
    }

    /**
     * String转Date
     * @param dateStr
     * @param format
     * 2016-1-17
     */
    public static Date stringToDate(String dateStr, String format) {
        if (dateStr == null || "".equals(dateStr)) {
            return null;
        }
        Date date = null;
        //注意format的格式要与日期String的格式相匹配
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            date = sdf.parse(dateStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }

    public static Date getDate(int year, int month, int day) {
        Calendar cal = Calendar.getInstance();
        cal.set(year, month, day);
        Date d = cal.getTime();
        return d;
    }


    public static Date getDate(int year, int month, int day,int hour,int minue,int second ) {
        Calendar cal = Calendar.getInstance();
        cal.set(year, month, day,hour,minue,second);
        Date d = cal.getTime();
        return d;
    }

    /**
     * Date转String
     *
     * @param date
     * @param format
    
     */
    public static String dateToString(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String currentDate = sdf.format(date);
        return currentDate;
    }

    /**
     * Date转Timestamp
     *
     * @param date
    
     */
    public static Timestamp dateToTimestamp(Date date) {
        Timestamp ts = new Timestamp(date.getTime());
        return ts;
    }

    /**
     * Timestamp转String
     *
     * @param ts
    
     */
    public static String timestampToString(Timestamp ts) {
        String tsStr = null;
        SimpleDateFormat sdf = new SimpleDateFormat(DATETIME_NORMAL_FORMAT);
        try {
            tsStr = sdf.format(ts);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tsStr;
    }

    /**
     * Timestamp转Date
     *
     * @param ts
    
     */
    public static Date timestampToDate(Timestamp ts) {
        return ts;
    }

    /**
     * 获得当前时间并格式化：yyyy-MM-dd HH:mm:ss
     *
     * @return
     */
    public static String getCurrentTimeNormal() {

        SimpleDateFormat sdf = new SimpleDateFormat(DATETIME_NORMAL_FORMAT);
        String currentDate = sdf.format(new Date());
        return currentDate;
    }

    /**
     * 获得当前时间并格式化：yyyyMMddHHmmss
     *
     * @return
     */
    public static String getCurrentTimeCompact() {

        SimpleDateFormat sdf = new SimpleDateFormat(DATETIME_COMPACT_FORMAT);
        String currentDate = sdf.format(new Date());
        return currentDate;
    }

    /**
     * 获得当前时间并格式化：yyyy-MM-dd
     *
     * @return
     */
    public static String getCurrentDateNormal() {

        SimpleDateFormat sdf = new SimpleDateFormat(DATE_NORMAL_FORMAT);
        String currentDate = sdf.format(new Date());
        return currentDate;
    }

    /**
     * 获得当前时间并格式化：yyyyMMdd
     *
     * @return
     */
    public static String getCurrentDateCompact() {

        SimpleDateFormat sdf = new SimpleDateFormat(DATE_COMPACT_FORMAT);
        String currentDate = sdf.format(new Date());
        return currentDate;
    }

    /**
     * 将20101202时间格式化为2010-12-02
     *
     * @param DateString 时间格式:yyyyMMdd
     * @return
     */
    public static String getDateCompactToNormal(String DateString) {

        StringBuilder sb = new StringBuilder();
        sb.append(DateString.substring(0, 4)).append("-").append(DateString.subSequence(4, 6)).append("-").append(DateString.substring(6, 8));
        return sb.toString();
    }

    /**
     * 将20101202101423时间格式化为2010-12-02 10:14:23
     *
     * @param DateString 时间格式:yyyyMMddHHmmss
     * @return
     */
    public static String getDateTimeCompactToNormal(String DateString) {

        StringBuilder sb = new StringBuilder();
        sb.append(DateString.substring(0, 4)).append("-").append(DateString.subSequence(4, 6)).append("-").append(DateString.substring(6, 8))
                .append(" ").append(DateString.substring(8, 10)).append(":").append(DateString.substring(10, 12)).append(":").append(DateString.substring(12));
        return sb.toString();
    }

    /**
     * 把界面输入的时间转为间凑的时间字符串
     * 将2010-12-02 10:14:23时间格式化为20101202101423
     *
     * @param dateNormalStr String
     * @return String
     */
    public static String getCompactString(String dateNormalStr) {
        StringBuffer ret = new StringBuffer();
        try {
            ret.append(dateNormalStr.substring(0, 4));
            ret.append(dateNormalStr.substring(5, 7));
            ret.append(dateNormalStr.substring(8, 10));
            ret.append(dateNormalStr.substring(11, 13));
            ret.append(dateNormalStr.substring(14, 16));
            ret.append(dateNormalStr.substring(17, 19));
        } catch (Exception ex) {
            // 如果字串不够长度，则返回前面的部分
        }
        return ret.toString();
    }

    /**
     * 将20101202(101423)时间格式  获得年份
     *
     * @param DateString 时间格式:yyyyMMdd(HHmmss)
     * @return
     */
    public static String getYear(String DateString) {

        return DateString.substring(0, 4);
    }

    /**
     * 将20101202(101423)时间格式  获得月份
     *
     * @param DateString 时间格式:yyyyMMdd(HHmmss)
     * @return
     */
    public static String getMonth(String DateString) {

        return DateString.substring(4, 6);
    }

    /**
     * 将20101202时间格式  获得日期
     *
     * @param DateString 时间格式:yyyyMMdd
     * @return
     */
    public static String getDayNoTime(String DateString) {
        return DateString.substring(6);
    }

    /**
     * 获取当前日期之前的日期，按天数向前推
     *
     * @param numVal
     * @param dateFormat
    
     */
    public static String getBeforeDatePlusDay(int numVal, String dateFormat) {
        Calendar calendar = Calendar.getInstance();
        long currentTimeMillis = calendar.getTimeInMillis();

        long hourInMillis = 60 * 60 * 1000;
        long dVal = numVal * 24 * hourInMillis;

        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        String currentDate = sdf.format(currentTimeMillis - dVal);
        return currentDate;
    }

    /**
     * 获取当前日期之hou的日期，按天数向前推
     *
     * @param numVal
     * @param dateFormat
    
     */
    public static String getAfterDatePlusDay(int numVal, String dateFormat) {
        Calendar calendar = Calendar.getInstance();
        long currentTimeMillis = calendar.getTimeInMillis();

        long hourInMillis = 60 * 60 * 1000;
        long dVal = numVal * 24 * hourInMillis;

        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        String currentDate = sdf.format(currentTimeMillis + dVal);
        return currentDate;
    }

    /**
     * 获取当前日期之前的日期，按小时向前推
     *
     * @param numVal
     * @param dateFormat
    
     */
    public static String getBeforeDatePlusHour(int numVal, String dateFormat) {
        Calendar calendar = Calendar.getInstance();
        long currentTimeMillis = calendar.getTimeInMillis();

        long hourInMillis = 60 * 60 * 1000;
        long dVal = numVal * hourInMillis;

        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        String currentDate = sdf.format(currentTimeMillis - dVal);
        return currentDate;
    }

    /**
     * 获取当前日期之前的日期，按小时向前推
     *
     * @param numVal
     * @param dateFormat
    
     */
    public static String getAfterDatePlusHour(int numVal, String dateFormat) {
        Calendar calendar = Calendar.getInstance();
        long currentTimeMillis = calendar.getTimeInMillis();

        long hourInMillis = 60 * 60 * 1000;
        long dVal = numVal * hourInMillis;

        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        String currentDate = sdf.format(currentTimeMillis + dVal);
        return currentDate;
    }





    /**
     * 两个日期相差天数
     *
     * @param beginDate
     * @param endDate
  
     */
    public static int daysBetween(Date beginDate, Date endDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(beginDate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(endDate);
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24);
        return Integer.parseInt(String.valueOf(between_days));
    }

    /**
     * 获取某月天数
     *
     * @param year
     * @param month
  
     */
    public static int getMonthdays(int year, int month) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month - 1);
        return cal.getActualMaximum(Calendar.DATE);
    }

    /**
     * 给时间加减年份
     *
     * @param date
     * @param plusTime
  
     */
    public static Date getDatePlusYear(Date date, int plusTime) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.YEAR, plusTime);
        Date d = cal.getTime();
        return d;
    }

    /**
     * 给时间加减月份
     *
     * @param date
     * @param plusTime
  
     */
    public static Date getDatePlusMonth(Date date, int plusTime) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, plusTime);
        Date d = cal.getTime();
        return d;
    }

    /**
     * 给时间加减月份
     *
     * @param date
     * @param plusTime
  
     */
    public static Date getDatePlusMonth(long time, int plusTime) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(time);
        cal.add(Calendar.MONTH, plusTime);
        Date d = cal.getTime();
        return d;
    }

    /**
     * 给时间加减天数
     *
     * @param date
     * @param plusTime
  
     */
    public static Date getDatePlusDay(Date date, int plusTime) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, plusTime);
        Date d = cal.getTime();
        return d;
    }

    /**
     * 给时间加减天数
     *
     * @param date
     * @param plusTime
  
     */
    public static Date getDatePlusDay(long millis, int plusTime) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(millis);
        cal.add(Calendar.DATE, plusTime);
        Date d = cal.getTime();
        return d;
    }

    /**
     * 给时间加减小时
     *
     * @param date
     * @param plusTime
  
     */
    public static Date getDatePlusHour(Date date, int plusTime) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.HOUR, plusTime);
        Date d = cal.getTime();
        return d;
    }

    /**
     * 给时间加减分钟
     *
     * @param date
     * @param plusTime
  
     */
    public static Date getDatePlusMinute(Date date, int plusTime) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MINUTE, plusTime);
        Date d = cal.getTime();
        return d;
    }

    /**
     * 给时间加减秒
     *
     * @param date
     * @param plusTime
  
     */
    public static Date getDatePlusSecond(Date date, int plusTime) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.SECOND, plusTime);
        Date d = cal.getTime();
        return d;
    }

    /**
     * 返回当前年
     *
  
     */
    public static int getCurrentYear() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.YEAR);
    }

    /**
     * 返回当前月
     *
  
     */
    public static int getCurrentMonth() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * 返回当前天
     *
  
     */
    public static int getCurrentDay() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 返回当前小时
     *
  
     */
    public static int getCurrentHour() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * 返回当前分钟
     *
  
     */
    public static int getCurrentMinute() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.MINUTE);
    }

    /**
     * 返回当前秒
     *
  
     */
    public static int getCurrentSecond() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.SECOND);
    }

    /**
     * 返回当前年
     *
  
     */
    public static int getYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }

    /**
     * 返回当前月
     *
  
     */
    public static int getMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * 返回当前天
     *
  
     */
    public static int getDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 返回当前小时
     *
  
     */
    public static int getHour(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * 返回当前分钟
     *
  
     */
    public static int getMinute(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MINUTE);
    }

    /**
     * 返回当前秒
     *
  
     */
    public static int getSecond(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.SECOND);
    }

    /**
     * 返回当前年
     *
  
     */
    public static int getYear(long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        return calendar.get(Calendar.YEAR);
    }

    /**
     * 返回当前月
     *
  
     */
    public static int getMonth(long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * 返回当前天
     *
  
     */
    public static int getDay(long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 返回当前小时
     *
  
     */
    public static int getHour(long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * 返回当前分钟
     *
  
     */
    public static int getMinute(long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        return calendar.get(Calendar.MINUTE);
    }

    /**
     * 返回当前秒
     *
  
     */
    public static int getSecond(long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        return calendar.get(Calendar.SECOND);
    }

    /**
     * 根据提供的年月日获取该月份的第一天
     *
     * @param date
     * @return
     * @Description: (这里用一句话描述这个方法的作用)
     * @Author: gyz
     * @Since: 2017-1-9下午2:26:57
     */
    public static String getSupportBeginDayofMonth(Date date) {
        date.getTime();
        Calendar startDate = Calendar.getInstance();
        startDate.setTime(date);
        startDate.set(Calendar.DAY_OF_MONTH, 1);
        startDate.set(Calendar.HOUR_OF_DAY, 0);
        startDate.set(Calendar.MINUTE, 0);
        startDate.set(Calendar.SECOND, 0);
        startDate.set(Calendar.MILLISECOND, 0);
        Date firstDate = startDate.getTime();
        return (firstDate.getTime() + "").substring(0, 10);

    }

    /**
     * 根据提供的年月获取该月份的最后一天
     *
     * @param date
     * @return
     * @Description: (这里用一句话描述这个方法的作用)
     * @Author: gyz
     * @Since: 2017-1-9下午2:29:38
     */
    public static String getSupportEndDayofMonth(Date date) {
        Calendar startDate = Calendar.getInstance();
        startDate.setTime(date);
        startDate.set(Calendar.DAY_OF_MONTH, startDate.getActualMaximum(Calendar.DAY_OF_MONTH));
        startDate.set(Calendar.HOUR_OF_DAY, 23);
        startDate.set(Calendar.MINUTE, 59);
        startDate.set(Calendar.SECOND, 59);
        startDate.set(Calendar.MILLISECOND, 999);
        Date firstDate = startDate.getTime();
        return (firstDate.getTime() + "").substring(0, 10);
    }

    /**
     * 当天的开始时间
     *
     * @return
     */
    public static long startOfTodDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date date = calendar.getTime();
        return date.getTime();
    }

    /**
     * 当天的开始时间
     *
     * @return
     */
    public static long startOfDate(long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date dt = calendar.getTime();
        return dt.getTime();
    }

    /**
     * 当天的结束时间
     *
     * @return
     */
    public static long endOfTodDay(long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        Date dt = calendar.getTime();
        return dt.getTime();
    }

    /**
     * 当天的开始时间
     *
     * @return
     */
    public static long startOfDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date dt = calendar.getTime();
        return dt.getTime();
    }

    /**
     * 当天的结束时间
     *
     * @return
     */
    public static long endOfTodDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        Date date = calendar.getTime();
        return date.getTime();
    }
}
