package com.dailycodebuffer.common.util;

import com.dailycodebuffer.common.common.CommonConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateTimeUtil {
    private static final Logger logger = LoggerFactory.getLogger(DateTimeUtil.class);
    public static final SimpleDateFormat DATE_FORMAT_1 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
    public static final SimpleDateFormat DATE_FORMAT_2 = new SimpleDateFormat("dd-MM-yyyy");
    public static final SimpleDateFormat DATE_FORMAT_3 = new SimpleDateFormat("MM-yyyy");
    public static final SimpleDateFormat DATE_FORMAT_4 = new SimpleDateFormat("yyyy-MM-dd");
    public static final SimpleDateFormat DATE_FORMAT_5 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSS");
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
    private static SimpleDateFormat sdf2 = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
    public final static DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");

    public static Long convertTimeStampToMilli(String time){
        Long t = null;
        if (time != null) {
            Date date = null;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            try {
                date = sdf.parse(time);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (date != null){
                t = date.getTime();
            }
        }
        return t;
    }
    public static String todayString() {
        String result = sdf.format(new Date());
        return result;
    }

    public static String todayString(String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        String result = sdf.format(new Date());
        return result;
    }

    public static String today() {
        String result = sdf2.format(new Date());
        return result;
    }

    public static Timestamp curentTimeStamp() {
        return new Timestamp(System.currentTimeMillis());
    }

    public static java.sql.Date curentSqlDate() {
        return new java.sql.Date(System.currentTimeMillis());
    }

    public static Timestamp parseStringToTimeStamp(String dateTime) {
        if (dateTime != null && !dateTime.equals("")) {
            return parseStringToTimeStamp(dateTime, "dd-MM-yyyy HH:mm:ss");
        } else {
            return null;
        }
    }

    public static Timestamp parseStringToTimeStamp(String dateTime, String pattern) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            Date date = sdf.parse(dateTime);
            return new Timestamp(date.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String parseTimeStampToString(Timestamp dateTime) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            String result = sdf.format(dateTime);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String parseTimeStampToString(Timestamp dateTime, String pattern) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            String result = sdf.format(dateTime);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static boolean compareDates(String d1, String d2) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            Date date1 = sdf.parse(d1);
            Date date2 = sdf.parse(d2);
            if (date1.after(date2)) {
                return false;
            }

            if (date1.before(date2)) {
                return true;
            }

            if (date1.equals(date2)) {
                return true;
            }

        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public static boolean compareDates(Timestamp d1, Timestamp d2) {
        try {
            if (d1.after(d2)) {
                return false;
            }

            if (d1.before(d2)) {
                return true;
            }

            if (d1.equals(d1)) {
                return true;
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
        return false;
    }

    public static boolean isExpired(Timestamp d) {
        if (!compareDates(d, curentTimeStamp())) {
            return true;
        }
        return false;
    }

    public static long betweenInMinutes(Date d1, Date d2) {
        long diffMs = d1.getTime() - d2.getTime();
        long diffSec = diffMs / 1000;
        long min = diffSec / 60;
        long sec = diffSec % 60;
        System.out.println("The difference is " + min + " minutes and " + sec + " seconds.");
        return min;
    }

    public static long betweenInMinutes(Timestamp d1, Timestamp d2) {
        long diffMs = d1.getTime() - d2.getTime();
        long diffSec = diffMs / 1000;
        long min = diffSec / 60;
        long sec = diffSec % 60;
        System.out.println("The difference is " + min + " minutes and " + sec + " seconds.");
        return min;
    }

    public static long betweenInDay(Timestamp d1, Timestamp d2) {
        long diffMs = d1.getTime() - d2.getTime();
        long diffSec = diffMs / 1000;
        long day = diffSec / 60 / 60 / 24;
        return day;
    }

    public static Integer currentHour() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH");
        String result = sdf.format(new Date());
        return Integer.valueOf(result);
    }
    public static Timestamp covertToTimestamp(String formatDateTime, String data) {
        try {
            DateFormat format = new SimpleDateFormat(formatDateTime);
            Date date = format.parse(data);
            Timestamp timestamp = new Timestamp(date.getTime());
            return timestamp;
        } catch (ParseException e) {
            logger.error(e.getMessage(), e);
            return null;
        }

    }

    public static Date covertToDate(String formatDateTime, String data) {
        try {
            SimpleDateFormat format = new SimpleDateFormat(formatDateTime);
            format.setLenient(false);
            return format.parse(data);
        } catch (ParseException e) {
            logger.error(e.getMessage(), e);
            return null;
        }

    }

    public static Timestamp getFrom(List<Timestamp> times){
        return times != null && times.size() >= 1 ? times.get(0) : null;
    }
    public static Timestamp getTo(List<Timestamp> times){
        return times != null && times.size() >= 2 ? times.get(1) : null;
    }

    public static List<Timestamp> getTimeToCompare(String typeSearch, Timestamp f , Timestamp t){
        Timestamp from;
        Timestamp to;
        Timestamp now = new Timestamp(System.currentTimeMillis());
        Date nowDate = new Date();
        logger.info("Current Timezone - [{}]", now);
        Calendar cFrom = Calendar.getInstance();
        if (typeSearch != null){
            if (typeSearch.equalsIgnoreCase(CommonConstants.SEARCH_DAY)) {
                from = f != null ? moveToBeginTimeOfDay(f) : null;
                to = t != null ? move2EndTimeOfDay(t) : null;
            } else if (typeSearch.equalsIgnoreCase(CommonConstants.CURRENT_WEEK)) {
                from = moveToBeginTimeOfDay(new Timestamp(firstDayOfCurrentWeek(nowDate).getTime()));
                to = move2EndTimeOfDay(new Timestamp(lastDayOfCurrentWeek(new Date()).getTime()));
            } else if (typeSearch.equalsIgnoreCase(CommonConstants.CURRENT_MONTH)) {
                cFrom.set(Calendar.DAY_OF_MONTH, 1);
                from =  moveToBeginTimeOfDay(new Timestamp(cFrom.getTimeInMillis()));
                to = getTo(from, cFrom);
            } else if (typeSearch.equalsIgnoreCase(CommonConstants.LAST_WEEK)) {
                from = moveToBeginTimeOfDay(new Timestamp(firstDayOfLastWeek(nowDate).getTime()));
                to = move2EndTimeOfDay(new Timestamp(lastDayOfLastWeek(new Date()).getTime()));
            } else if (typeSearch.equalsIgnoreCase(CommonConstants.LAST_MONTH)) {
                cFrom.add(Calendar.MONTH, -1);
                cFrom.set(Calendar.DAY_OF_MONTH, 1);
                from = moveToBeginTimeOfDay(new Timestamp(cFrom.getTimeInMillis()));
                to = getTo(from, cFrom);
            }else {
                return null;
            }
        }else {
            return null;
        }
        List<Timestamp> result = new ArrayList<>();
        result.add(from);
        result.add(to);
        return result;
    }

    public static Date firstDayOfLastWeek(Date date) {
        Calendar c = Calendar.getInstance();
        c = (Calendar) c.clone();
        // last week
        c.add(Calendar.WEEK_OF_YEAR, -1);
        // first day
        c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek());
        return c.getTime();
    }

    public static Date lastDayOfLastWeek(Date date) {
        Calendar c = Calendar.getInstance();
        c = (Calendar) c.clone();
        // first day of this week
        c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek());
        // last day of previous week
        c.add(Calendar.DAY_OF_MONTH, -1);
        return c.getTime();
    }

    public static Date firstDayOfCurrentWeek(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
        return cal.getTime();
    }

    public static Date lastDayOfCurrentWeek(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, 6);// last day of week
        return cal.getTime();
    }

    public static Timestamp getTo(Timestamp from, Calendar cFrom){
        Calendar c1 = Calendar.getInstance();
        c1.setTime(new Date(from.getTime()));
        c1.set(Calendar.DAY_OF_MONTH, cFrom.getActualMaximum(Calendar.DAY_OF_MONTH));
        return moveToBeginTimeOfDay(new Timestamp(c1.getTimeInMillis()));
    }

    public static Timestamp moveToBeginTimeOfDay(Timestamp input) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(input.getTime());
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return new Timestamp(cal.getTimeInMillis());
    }
    public static Timestamp move2EndTimeOfDay(Timestamp input) {
        Timestamp res = null;
        if (input != null) {
            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(input.getTime());
            cal.set(Calendar.HOUR_OF_DAY, 23);
            cal.set(Calendar.MINUTE, 59);
            cal.set(Calendar.SECOND, 59);
            cal.set(Calendar.MILLISECOND, 999);
            res = new Timestamp(cal.getTimeInMillis());
        }

        return res;
    }

    public static Timestamp string2Timestamp(String date, String format) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(format);
            Date parsedDate = dateFormat.parse(date);
            Timestamp timestamp = new Timestamp(parsedDate.getTime());
            return timestamp;
        } catch (Exception e) {
            return null;
        }
    }

    public static Boolean isBeforeToday(Date inputDate){ // format: MM-dd-yyyy
        Timestamp ts = DateTimeUtil.moveToBeginTimeOfDay(new Timestamp(System.currentTimeMillis()));
        Date today = new Date(ts.getTime());
        return inputDate.compareTo(today) < 0;
    }

    public static Boolean isStringTimeStampInThePast(String stringTimeStamp){// format: MM-dd-yyyy HH:ss
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(CommonConstants.DATE_AND_TIME_FORMAT);
            Date parsedDate = dateFormat.parse(stringTimeStamp);
            Timestamp timestamp = new Timestamp(parsedDate.getTime());
            System.out.println(timestamp);
            if (timestamp.before(new Timestamp(System.currentTimeMillis()))){
                return true;
            }
        } catch(Exception e) {
            logger.info(e.getMessage());
        }
        return false;
    }

    public static String formatLocalDateTimeNow() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return LocalDateTime.now().format(formatter);
    }
}
