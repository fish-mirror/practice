package com.zjicm.common.lang.util;

import com.zjicm.common.lang.consts.NumberConsts;
import com.zjicm.common.lang.consts.StringConsts;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public final class TimeUtil {
    public static final Date DATE_AS_NULL = new Date(1l);

    public static Calendar getCalendar() {
        return getCalendar(null);
    }

    public static Calendar getCalendar(Date date) {
        Calendar cal = Calendar.getInstance();
        if (date != null) {
            cal.setTime(date);
        }
        return cal;
    }

    public static int toSeconds() {
        return (int) (System.currentTimeMillis() / 1000);
    }

    public static int toSeconds(Date date) {
        if (date != null) {
            return (int) (date.getTime() / 1000);
        }

        return 0;
    }

    public static String toString(Date date) {
        return toString(null, date);
    }

    public static String toString(String pattern, Date date) {
        return new SimpleDateFormat(pattern == null ? StringConsts.DATE_FORMAT_PATTERN_DEFAULT : pattern).format(
                date == null ? new Date() : date);
    }

    private static Calendar floor(Calendar cal) {
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal;
    }

    public static Date floor() {
        return floor(null, 0);
    }

    public static Date floor(int delta) {
        return floor(null, delta);
    }

    public static Date floor(Date date) {
        return floor(date, 0);
    }

    public static Date floor(Date date, int delta) {
        Calendar cal = floor(getCalendar(date));
        if (delta != 0) {
            cal.add(Calendar.DATE, delta);
        }

        return cal.getTime();
    }

    public static boolean between(Date value, Date min, Date max) {
        if (value != null && (min != null || max != null)) {
            if (min == null) {
                return value.before(max);
            }

            if (max == null) {
                return value.after(min);
            }

            return value.after(min) || value.before(max);
        }
        return false;
    }

    private static Calendar ceiling(Calendar cal) {
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);
        return cal;
    }

    public static Date ceiling() {
        return ceiling(null, 0);
    }

    public static Date ceiling(int delta) {
        return ceiling(null, delta);
    }

    public static Date ceiling(Date date) {
        return ceiling(date, 0);
    }

    public static Date ceiling(Date date, int delta) {
        Calendar cal = ceiling(getCalendar(date));
        if (delta != 0) {
            cal.add(Calendar.DATE, delta);
        }
        return cal.getTime();
    }

    public static Date delta(int days) {
        return delta(days, TimeUnit.DAYS);
    }

    public static Date delta(long duration, TimeUnit timeUnit) {
        if (timeUnit == null) {
            timeUnit = TimeUnit.DAYS;
        }

        return new Date(System.currentTimeMillis() + timeUnit.toMillis(duration));
    }

    public static Date toMonthFirst() {
        return toMonthFirst(new Date());
    }

    public static Date toMonthFirst(Date date) {
        Calendar c = floor(getCalendar(date));
        c.set(Calendar.DAY_OF_MONTH, 1);
        return c.getTime();
    }

    public static Date toMonthLast() {
        return toMonthLast(new Date());
    }

    public static Date toMonthLast(Date date) {
        Calendar c = ceiling(getCalendar(date));
        c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DATE));
        return c.getTime();
    }

    public static Date toNextMonthFirst(Date date) {
        Calendar c = floor(getCalendar(date));
        c.set(Calendar.DAY_OF_MONTH, 1);
        c.add(Calendar.MONTH, 1);
        return c.getTime();
    }

    public static Date toNextMonthLast(Date date) {
        Calendar c = ceiling(getCalendar(date));
        c.set(Calendar.DAY_OF_MONTH, 1);
        c.add(Calendar.MONTH, 1);
        c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DATE));
        return c.getTime();
    }

    public static int toInt() {
        return toInt(new Date());
    }

    public static int toInt(Date date) {
        if (date != null) {
            return Integer.parseInt(format(date, StringConsts.DATE_FORMAT_PATTERN_INT));
        }
        return 0;
    }

    public static int toMonthInt(Date date) {
        if (date == null) {
            date = new Date();
        }
        return Integer.parseInt(format(date, StringConsts.DATE_FORMAT_PATTERN_MONTH_INT));
    }

    public static Date parse(int dateId) {
        if (dateId > 0) {
            try {
                return new SimpleDateFormat(StringConsts.DATE_FORMAT_PATTERN_INT).parse(String.valueOf(dateId));
            } catch (Throwable e) {
            }
        }

        return null;
    }

    public static Date parseFromLongString(String longStr) {
        if (NumberUtil.is(longStr)) {
            long value = NumberUtil.parseLongQuietly(longStr, 0l);
            if (value > 0) {
                return new Date(value);
            }
        }
        return null;
    }

    public static String format(Date date) {
        return format(date, StringConsts.DATE_FORMAT_PATTERN_DEFAULT);
    }

    public static String format(Date date, String formatString) {
        if (date == null) {
            date = new Date();
        }
        return new SimpleDateFormat(formatString).format(date);
    }

    public static Date parse(String dateString) {
        if (dateString != null) {
            try {
                if (dateString.length() == StringConsts.DATE_FORMAT_PATTERN_INT.length()) {
                    return new SimpleDateFormat(StringConsts.DATE_FORMAT_PATTERN_INT).parse(dateString);
                }
                if (dateString.length() == StringConsts.DATE_FORMAT_PATTERN_DEFAULT.length()) {
                    return new SimpleDateFormat(StringConsts.DATE_FORMAT_PATTERN_DEFAULT).parse(dateString);
                }
                if (dateString.length() < StringConsts.DATE_FORMAT_PATTERN_DEFAULT.length()) {
                    return new SimpleDateFormat(StringConsts.DATE_FORMAT_PATTERN_DEFAULT.substring(0,
                                                                                                   dateString.length()))
                            .parse(dateString);
                }
            } catch (ParseException e) {
            }
        }

        return null;
    }

    public static int getYearFrom(int dateInt) {
        if (dateInt > 10000000 && dateInt < 99999999) {
            return Calendar.getInstance().get(Calendar.YEAR) - dateInt / NumberConsts.TEN_THOUSAND;
        }
        return 0;
    }

    public static Date toWeekFirst(Date date) {
        Calendar c = floor(getCalendar(date));
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        if (dayOfWeek != 2) {
            if (dayOfWeek == 1) {
                c.add(Calendar.DATE, -6);
            } else {
                c.add(Calendar.DATE, 2 - dayOfWeek);
            }
        }

        return c.getTime();
    }

    public static Date toWeekend(Date date) {
        Calendar c = ceiling(getCalendar(date));
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        if (dayOfWeek != 1) {
            c.add(Calendar.DATE, 8 - dayOfWeek);
        }

        return c.getTime();
    }

    public static void main(String[] args) {
    }
}
