package com.gamesky.card.core.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * lianghongbin on 15/8/21.
 */
public class DateUtils {

    public static boolean sameDay(long one, long two) {
        SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = dataFormat.format(one);
        String now = dataFormat.format(two);
        return date.equals(now);
    }

    public static long minTime(long time) {
        SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = dataFormat.format(time);

        try {
            Date d = dataFormat.parse(date + " 00:00:00");
            return d.getTime();
        } catch (ParseException ignored) {
        }

        return 0L;
    }

    public static long maxTime(long time) {
        SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = dataFormat.format(time);

        try {
            Date d = dataFormat.parse(date + " 23:59:59");
            return d.getTime();
        } catch (ParseException ignored) {
        }

        return 0L;
    }

    public static boolean nextDay(long first, long second) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTimeInMillis(first);
        calendar.add(Calendar.DATE, 1);//把日期往后增加一天.整数往后推,负数往前移动
        Date date = calendar.getTime();   //这个时间就是日期往后推一天的结果

        return sameDay(date.getTime(), second);
    }
}
