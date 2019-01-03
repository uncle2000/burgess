package com.uncle2000.lib.uitils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateFormat {
    public static final int YEAR_TIME = 1000;
    public static final int MONEY_TIME = 1000;
    public static final int DATE_TIME = 1000 * 60 * 60 * 24;
    public static final int HOUR_TIME = 1000 * 60 * 60;
    public static final int MINYTE_TIME = 1000 * 60;
    public static final int SECOND_TIME = 1000;
    //
    public final static SimpleDateFormat sdf_1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public final static SimpleDateFormat sdf_19 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    public final static SimpleDateFormat sdf_21 = new SimpleDateFormat("MM-dd HH:mm");
    public final static SimpleDateFormat sdf_2 = new SimpleDateFormat("yyyy-MM-dd");
    public final static SimpleDateFormat sdf_3 = new SimpleDateFormat("yyyy/MM/dd");
    public final static SimpleDateFormat sdf_20 = new SimpleDateFormat("yyyy/MM-dd");
    public final static SimpleDateFormat sdf_4 = new SimpleDateFormat("yyyyMMdd");
    public final static SimpleDateFormat sdf_5 = new SimpleDateFormat("yyyy年MM月");
    public final static SimpleDateFormat sdf_6 = new SimpleDateFormat("yyyy/MM/dd HH:mm");
    public final static SimpleDateFormat sdf_7 = new SimpleDateFormat("yyyy/MM/dd");
    public final static SimpleDateFormat sdf_8 = new SimpleDateFormat("HH:mm");
    public final static SimpleDateFormat sdf_9 = new SimpleDateFormat("HH");
    public final static SimpleDateFormat sdf_10 = new SimpleDateFormat("mm");
    public final static SimpleDateFormat sdf_11 = new SimpleDateFormat("HHmm");
    public final static SimpleDateFormat sdf_12 = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
    public final static SimpleDateFormat sdf_13 = new SimpleDateFormat("HHmmss");
    public final static SimpleDateFormat sdf_14 = new SimpleDateFormat("yyyyMM");
    public final static SimpleDateFormat sdf_15 = new SimpleDateFormat("MM月dd日");
    public final static SimpleDateFormat sdf_16 = new SimpleDateFormat("HH:mm");
    public final static SimpleDateFormat sdf_17 = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
    public final static SimpleDateFormat sdf_18 = new SimpleDateFormat("MM-dd HH:mm");
    public final static SimpleDateFormat sdf_22 = new SimpleDateFormat("hh:mm a", Locale.ENGLISH);
    public final static SimpleDateFormat sdf_23 = new SimpleDateFormat("yyyy-MM-dd HH:mm a", Locale.ENGLISH);
    public final static SimpleDateFormat sdf_24 = new SimpleDateFormat("dd-MM HH:mm a", Locale.ENGLISH);
    public final static SimpleDateFormat sdf_25 = new SimpleDateFormat("dd-MM-yyyy");
    public final static SimpleDateFormat sdf_26 = new SimpleDateFormat("dd-MM");


    static Calendar c = Calendar.getInstance();
    //	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
    //	private static SimpleDateFormat stf = new SimpleDateFormat("HHmm");
    private static SimpleDateFormat FORMAT_YMD = new SimpleDateFormat("yyyy-MM-dd");
    private static SimpleDateFormat FORMAT_MD = new SimpleDateFormat("MM-dd");

    /**
     * 将日期转换为 yyyy-MM-dd 的int值
     *
     * @param date
     * @return
     */
    public static String dateToString(Date date) {
        return sdf_2.format(date);
    }

    public static String dateToString(long date) {
        // c.setTimeInMillis(date);
        return sdf_3.format(new Date(date));
    }

    public static String dateToString1(long date) {
        // c.setTimeInMillis(date);
        return sdf_1.format(new Date(date));
    }

    public static int dateToInt(long date) {
        // c.setTimeInMillis(date);
        return Integer.parseInt(sdf_14.format(new Date(date)));
    }

    public static int dateToInt3(long date) {
        // c.setTimeInMillis(date);
        return Integer.parseInt(sdf_4.format(new Date(date)));
    }

    public static int getHour(long date) {
        // c.setTimeInMillis(date);
        return Integer.parseInt(sdf_9.format(new Date(date)));
    }

    public static int getM(long date) {
        // c.setTimeInMillis(date);
        return Integer.parseInt(sdf_10.format(new Date(date)));
    }

    public static int dateToInt2(long date) {
        // c.setTimeInMillis(date);
        return Integer.parseInt(sdf_11.format(new Date(date)));
    }

    public static String dateToString(SimpleDateFormat dateFormat, long date) {
        // c.setTimeInMillis(date);
        return dateFormat.format(new Date(date));
    }

    public static String dateToString(SimpleDateFormat dateFormat, Date date) {
        // c.setTimeInMillis(date);
        return dateFormat.format(date);
    }

    public static String dateToString2(long date) {
        // c.setTimeInMillis(date);
        return sdf_5.format(new Date(date));
    }

    public static String dateToString3(long date) {
        // c.setTimeInMillis(date);
        return sdf_6.format(new Date(date));
    }

    public static String dateToString4(long date) {
        // c.setTimeInMillis(date);
        return sdf_7.format(new Date(date));
    }

    public static String dateToString5(long date) {
        // c.setTimeInMillis(date);
        return sdf_8.format(new Date(date));
    }

    public static String dateToString6(long date) {
        // c.setTimeInMillis(date);
        return sdf_12.format(new Date(date));
    }

    public static String dateToString15(long date) {
        // c.setTimeInMillis(date);
        return sdf_15.format(new Date(date));
    }

    public static String dateToString17(long date) {
        // c.setTimeInMillis(date);
        return sdf_17.format(new Date(date));
    }

    public static String dateToString16(long date) {
        // c.setTimeInMillis(date);
        return sdf_2.format(new Date(date));
    }

    public static String dateToString18(long date) {
        // c.setTimeInMillis(date);
        return sdf_18.format(new Date(date));
    }

    public static String dateToString19(long date) {
        // c.setTimeInMillis(date);
        return sdf_19.format(new Date(date));
    }

    public static String dateToString21(long date) {
        // c.setTimeInMillis(date);
        return sdf_21.format(new Date(date));
    }

    public static String dateToString22(long date) {
        return sdf_22.format(new Date(date));
    }

    public static String dateToString23(long date) {
        return sdf_23.format(new Date(date));
    }

    public static String dateToString24(long date) {
        return sdf_24.format(new Date(date));
    }

    public static String dateToString25(long date) {
        return sdf_25.format(new Date(date));
    }

    public static String dateToString26(long date) {
        return sdf_26.format(new Date(date));
    }

    public static int dateToshijiancha(long date) {
        //		Calendar a=Calendar.getInstance();
        //		a.setTimeInMillis(day);
        Calendar b = Calendar.getInstance();
        long bb = b.getTimeInMillis();

        return Integer.parseInt(sdf_13.format(bb)) - Integer.parseInt(sdf_13.format(date));
    }

    public static String getSimpleTime(long date) {
        if (date == 0)
            return "";
        Calendar now = Calendar.getInstance();
        Calendar yet = Calendar.getInstance();
        yet.setTimeInMillis(date);

        int nowDate = DateFormat.dateToInt3(now.getTimeInMillis());
        //		int nowTime=DateFormat.dateToInt2(now.getTimeInMillis());
        //		int nowHour=DateFormat.getHour(now.getTimeInMillis());
        //		int nowM=DateFormat.getM(now.getTimeInMillis());

        int cdate = DateFormat.dateToInt3(yet.getTimeInMillis());
        //		int time=DateFormat.dateToInt2(c.getTimeInMillis());
        //		int hour=DateFormat.getHour(c.getTimeInMillis());
        //		int m=DateFormat.getM(c.getTimeInMillis());

        if (nowDate == cdate) {
            return sdf_8.format(yet.getTimeInMillis());
        } else if ((nowDate - cdate) == 1) {
            return "昨天 " + sdf_8.format(yet.getTimeInMillis());
        } else if ((nowDate - cdate) <= 7) {
            String str = "";
            switch (yet.get(Calendar.DAY_OF_WEEK)) {
                case 0:
                    str = "六";
                    //					str = "日";
                    break;
                case 1:
                    str = "日";
                    //					str = "一";
                    break;
                case 2:
                    //					str = "二";
                    str = "一";
                    break;
                case 3:
                    //					str = "三";
                    str = "二";
                    break;
                case 4:
                    //					str = "四";
                    str = "三";
                    break;
                case 5:
                    //					str = "五";
                    str = "四";
                    break;
                case 6:
                    //					str = "六";
                    str = "五";
                    break;
                case 7:
                    str = "六";
                    break;
            }
            return "星期" + str + " " + sdf_8.format(yet.getTimeInMillis());
        } else {
            return sdf_12.format(yet.getTimeInMillis());
        }
    }

    public static String getSimpleTime2(long date) {
        if (date == 0)
            return "";
        Calendar now = Calendar.getInstance();
        Calendar yet = Calendar.getInstance();
        yet.setTimeInMillis(date);

        int nowDate = DateFormat.dateToInt3(now.getTimeInMillis());
        //		int nowTime=DateFormat.dateToInt2(now.getTimeInMillis());
        //		int nowHour=DateFormat.getHour(now.getTimeInMillis());
        //		int nowM=DateFormat.getM(now.getTimeInMillis());

        int cdate = DateFormat.dateToInt3(yet.getTimeInMillis());
        //		int time=DateFormat.dateToInt2(c.getTimeInMillis());
        //		int hour=DateFormat.getHour(c.getTimeInMillis());
        //		int m=DateFormat.getM(c.getTimeInMillis());

        if (nowDate == cdate) {
            return sdf_8.format(yet.getTimeInMillis());
        } else if ((nowDate - cdate) == 1) {
            return "昨天";
        } else if ((nowDate - cdate) <= 7) {
            String str = "";
            switch (yet.get(Calendar.DAY_OF_WEEK) - 1) {
                case 0:
                    str = "日";
                    break;
                case 1:
                    str = "一";
                    break;
                case 2:
                    str = "二";
                    break;
                case 3:
                    str = "三";
                    break;
                case 4:
                    str = "四";
                    break;
                case 5:
                    str = "五";
                    break;
                case 6:
                    str = "六";
                    break;
                default:
                    return sdf_3.format(yet.getTimeInMillis());
            }
            return "星期" + str;
        } else {
            return sdf_3.format(yet.getTimeInMillis());
        }
    }

    public static String getSimpleTime3(long date) {
        if (date == 0)
            return "";
        Calendar now = Calendar.getInstance();
        Calendar yet = Calendar.getInstance();
        yet.setTimeInMillis(date);

        int nowDate = DateFormat.dateToInt3(now.getTimeInMillis());
        //		int nowTime=DateFormat.dateToInt2(now.getTimeInMillis());
        //		int nowHour=DateFormat.getHour(now.getTimeInMillis());
        //		int nowM=DateFormat.getM(now.getTimeInMillis());

        int cdate = DateFormat.dateToInt3(yet.getTimeInMillis());
        //		int time=DateFormat.dateToInt2(c.getTimeInMillis());
        //		int hour=DateFormat.getHour(c.getTimeInMillis());
        //		int m=DateFormat.getM(c.getTimeInMillis());

        if (nowDate == cdate) {
            return "今天";
        } else if ((nowDate - cdate) == 1) {
            return "昨天";
        } else if ((nowDate - cdate) <= 7) {
            String str = "";
            switch (yet.get(Calendar.DAY_OF_WEEK) - 1) {
                case 0:
                    str = "日";
                    break;
                case 1:
                    str = "一";
                    break;
                case 2:
                    str = "二";
                    break;
                case 3:
                    str = "三";
                    break;
                case 4:
                    str = "四";
                    break;
                case 5:
                    str = "五";
                    break;
                case 6:
                    str = "六";
                    break;
                default:
                    return sdf_20.format(yet.getTimeInMillis());
            }
            return "星期" + str;
        } else {
            return sdf_20.format(yet.getTimeInMillis());
        }
    }

    /**
     * 当年的日期不显示年份，不是当年的日期显示年份
     *
     * @param date
     * @return
     */
    public static String getCurrentYearFormatTime(long date) {

        if (date == 0) {
            return "";
        }
        Calendar now = Calendar.getInstance();
        Calendar yet = Calendar.getInstance();
        yet.setTimeInMillis(date);
        if (now.get(Calendar.YEAR) == yet.get(Calendar.YEAR)) {
            return dateToString(new SimpleDateFormat("MM-dd HH:mm"), date);
        } else {
            return dateToString(new SimpleDateFormat("yyyy-MM-dd"), date);
        }
    }

    public static DateModel parsDateModel(String time) {
        try {
            return parsDateModel(Long.valueOf(time));
        } catch (Exception e) {
            return null;
        }
    }

    public static DateModel parsDateModel(long time) {
        DateModel model = new DateModel();
        model.day = time / DATE_TIME;
        model.hour = (time / HOUR_TIME - model.day * 24);
        model.minute = (time / MINYTE_TIME - model.day * 24 * 60 - model.hour * 60);
        model.second = (time / SECOND_TIME - model.day * 24 * 60 * 60 - model.hour * 60 * 60 - model.minute * 60);
        return model;
    }

    /**
     * 格式化时间，如果同一年，返回格式为12-12
     * 如果跨年返回格式  2016-12-12
     *
     * @param time
     * @return
     */
    public static String formatTimeByMdOrYmd(long time) {
        Calendar calendarTime = Calendar.getInstance();
        calendarTime.setTimeInMillis(time);

        Calendar calendarNow = Calendar.getInstance();
        calendarNow.setTimeInMillis(System.currentTimeMillis());

        if (calendarTime.get(Calendar.YEAR) == calendarNow.get(Calendar.YEAR)) { // 同一年，返回12-24
            return FORMAT_MD.format(new Date(time));
        }
        return FORMAT_YMD.format(new Date(time)); // 不同年，返回2016-12-24
    }

    public static class DateModel {
        public long year = 0;
        public long money = 0;
        public long day = 0;
        public long hour = 0;
        public long minute = 0;
        public long second = 0;

        @Override
        public String toString() {
            String buffer = "[year=%d,money=%d,day=%d,hour=%d,minute=%d,second=%d]";
            return String.format(buffer, year, money, day, hour, minute, second);
        }
    }

    /**
     * 判断日期是否为今天
     *
     * @param date
     * @return
     */
    public static boolean isToday(long date) {
        Date now = new Date();
        Date thatDate = new Date(date);

        Calendar calendarNow = Calendar.getInstance();
        calendarNow.setTime(now);

        Calendar calendarThatDay = Calendar.getInstance();
        calendarThatDay.setTime(thatDate);

        return calendarNow.get(Calendar.YEAR) == calendarThatDay.get(Calendar.YEAR)
                && calendarNow.get(Calendar.MONTH) == calendarThatDay.get(Calendar.MONTH)
                && calendarNow.get(Calendar.DATE) == calendarThatDay.get(Calendar.DATE);
    }


    /**
     * 判断日期是否为今天
     *
     * @param date
     * @return
     */
    public static boolean isYesterday(long date) {
        Date now = new Date();
        Date thatDate = new Date(date);

        Calendar calendarNow = Calendar.getInstance();
        calendarNow.setTime(now);

        Calendar calendarThatDay = Calendar.getInstance();
        calendarThatDay.setTime(thatDate);
        calendarThatDay.roll(Calendar.DATE, true);

        return calendarNow.get(Calendar.YEAR) == calendarThatDay.get(Calendar.YEAR)
                && calendarNow.get(Calendar.MONTH) == calendarThatDay.get(Calendar.MONTH)
                && calendarNow.get(Calendar.DATE) == calendarThatDay.get(Calendar.DATE);
    }


    /**
     * 判断日期是否为同一天
     *
     * @return -1是去年 0是当天前 1是当天
     */
    public static int isSameDay(long date) {
        Date time = new Date(date);
        Date currentTime = new Date(System.currentTimeMillis());

        Calendar nowCalendar = Calendar.getInstance();
        nowCalendar.setTime(currentTime);

        Calendar dateCalendar = Calendar.getInstance();
        dateCalendar.setTime(time);

        if (nowCalendar.get(Calendar.YEAR) == dateCalendar.get(Calendar.YEAR)

                && nowCalendar.get(Calendar.MONTH) == dateCalendar.get(Calendar.MONTH)

                && nowCalendar.get(Calendar.DATE) == dateCalendar.get(Calendar.DATE)) {

            return 1;
        } else if (nowCalendar.get(Calendar.YEAR) != dateCalendar.get(Calendar.YEAR)) {
            return -1;
        } else if (nowCalendar.get(Calendar.YEAR) == dateCalendar.get(Calendar.YEAR)

                && nowCalendar.get(Calendar.MONTH) == dateCalendar.get(Calendar.MONTH)

                && nowCalendar.get(Calendar.DATE) != dateCalendar.get(Calendar.DATE)) {
            return 0;
        }
        return -1;
    }


    /**
     * 判断是不是一天以前
     *
     * @param date
     * @return
     */
//    public static String isOneDayBefore(long date) {
//        if (System.currentTimeMillis() - date > 86400000) {
//            return dateToString24(date);
//        } else if (System.currentTimeMillis() - date < 86400000 && System.currentTimeMillis() - date > 3600000) {
//            long l = (System.currentTimeMillis() - date) / 1000 / 60 / 60;
//            return l + App.INSTANCE.getString(R.string.hour_before);
//        } else {
//            return App.INSTANCE.getString(R.string.just_now);
//        }
//    }

}
