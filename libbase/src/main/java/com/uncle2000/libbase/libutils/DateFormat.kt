package com.uncle2000.libbase.libutils

import com.uncle2000.libbase.R
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

object DateFormat {
    val YEAR_TIME = 1000
    val MONEY_TIME = 1000
    val DATE_TIME = 1000 * 60 * 60 * 24
    val HOUR_TIME = 1000 * 60 * 60
    val MINYTE_TIME = 1000 * 60
    val SECOND_TIME = 1000
    //
    val sdf_1 = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    val sdf_19 = SimpleDateFormat("yyyy-MM-dd HH:mm")
    val sdf_21 = SimpleDateFormat("MM-dd HH:mm")
    val sdf_2 = SimpleDateFormat("yyyy-MM-dd")
    val sdf_3 = SimpleDateFormat("yyyy/MM/dd")
    val sdf_20 = SimpleDateFormat("yyyy/MM-dd")
    val sdf_4 = SimpleDateFormat("yyyyMMdd")
    val sdf_5 = SimpleDateFormat("yyyy年MM月")
    val sdf_6 = SimpleDateFormat("yyyy/MM/dd HH:mm")
    val sdf_7 = SimpleDateFormat("yyyy/MM/dd")
    val sdf_8 = SimpleDateFormat("HH:mm")
    val sdf_9 = SimpleDateFormat("HH")
    val sdf_10 = SimpleDateFormat("mm")
    val sdf_11 = SimpleDateFormat("HHmm")
    val sdf_12 = SimpleDateFormat("yyyy年MM月dd日 HH:mm")
    val sdf_13 = SimpleDateFormat("HHmmss")
    val sdf_14 = SimpleDateFormat("yyyyMM")
    val sdf_15 = SimpleDateFormat("MM月dd日")
    val sdf_16 = SimpleDateFormat("HH:mm")
    val sdf_17 = SimpleDateFormat("yyyy.MM.dd HH:mm:ss")
    val sdf_18 = SimpleDateFormat("MM-dd HH:mm")
    val sdf_22 = SimpleDateFormat("hh:mm a", Locale.ENGLISH)
    val sdf_23 = SimpleDateFormat("yyyy-MM-dd HH:mm a", Locale.ENGLISH)
    val sdf_24 = SimpleDateFormat("dd-MM HH:mm a", Locale.ENGLISH)
    val sdf_25 = SimpleDateFormat("dd-MM-yyyy")
    val sdf_26 = SimpleDateFormat("dd-MM")


    internal var c = Calendar.getInstance()
    //	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
    //	private static SimpleDateFormat stf = new SimpleDateFormat("HHmm");
    private val FORMAT_YMD = SimpleDateFormat("yyyy-MM-dd")
    private val FORMAT_MD = SimpleDateFormat("MM-dd")

    /**
     * 将日期转换为 yyyy-MM-dd 的int值
     *
     * @param date
     * @return
     */
    fun dateToString(date: Date): String {
        return sdf_2.format(date)
    }

    fun dateToString(date: Long): String {
        // c.setTimeInMillis(date);
        return sdf_3.format(Date(date))
    }

    fun dateToString1(date: Long): String {
        // c.setTimeInMillis(date);
        return sdf_1.format(Date(date))
    }

    fun dateToInt(date: Long): Int {
        // c.setTimeInMillis(date);
        return Integer.parseInt(sdf_14.format(Date(date)))
    }

    fun dateToInt3(date: Long): Int {
        // c.setTimeInMillis(date);
        return Integer.parseInt(sdf_4.format(Date(date)))
    }

    fun getHour(date: Long): Int {
        // c.setTimeInMillis(date);
        return Integer.parseInt(sdf_9.format(Date(date)))
    }

    fun getM(date: Long): Int {
        // c.setTimeInMillis(date);
        return Integer.parseInt(sdf_10.format(Date(date)))
    }

    fun dateToInt2(date: Long): Int {
        // c.setTimeInMillis(date);
        return Integer.parseInt(sdf_11.format(Date(date)))
    }

    fun dateToString(dateFormat: SimpleDateFormat, date: Long): String {
        // c.setTimeInMillis(date);
        return dateFormat.format(Date(date))
    }

    fun dateToString(dateFormat: SimpleDateFormat, date: Date): String {
        // c.setTimeInMillis(date);
        return dateFormat.format(date)
    }

    fun dateToString2(date: Long): String {
        // c.setTimeInMillis(date);
        return sdf_5.format(Date(date))
    }

    fun dateToString3(date: Long): String {
        // c.setTimeInMillis(date);
        return sdf_6.format(Date(date))
    }

    fun dateToString4(date: Long): String {
        // c.setTimeInMillis(date);
        return sdf_7.format(Date(date))
    }

    fun dateToString5(date: Long): String {
        // c.setTimeInMillis(date);
        return sdf_8.format(Date(date))
    }

    fun dateToString6(date: Long): String {
        // c.setTimeInMillis(date);
        return sdf_12.format(Date(date))
    }

    fun dateToString15(date: Long): String {
        // c.setTimeInMillis(date);
        return sdf_15.format(Date(date))
    }

    fun dateToString17(date: Long): String {
        // c.setTimeInMillis(date);
        return sdf_17.format(Date(date))
    }

    fun dateToString16(date: Long): String {
        // c.setTimeInMillis(date);
        return sdf_2.format(Date(date))
    }

    fun dateToString18(date: Long): String {
        // c.setTimeInMillis(date);
        return sdf_18.format(Date(date))
    }

    fun dateToString19(date: Long): String {
        // c.setTimeInMillis(date);
        return sdf_19.format(Date(date))
    }

    fun dateToString21(date: Long): String {
        // c.setTimeInMillis(date);
        return sdf_21.format(Date(date))
    }

    fun dateToString22(date: Long): String {
        return sdf_22.format(Date(date))
    }

    fun dateToString23(date: Long): String {
        return sdf_23.format(Date(date))
    }

    fun dateToString24(date: Long): String {
        return sdf_24.format(Date(date))
    }

    fun dateToString25(date: Long): String {
        return sdf_25.format(Date(date))
    }

    fun dateToString26(date: Long): String {
        return sdf_26.format(Date(date))
    }

    fun dateToshijiancha(date: Long): Int {
        //		Calendar a=Calendar.getInstance();
        //		a.setTimeInMillis(day);
        val b = Calendar.getInstance()
        val bb = b.timeInMillis

        return Integer.parseInt(sdf_13.format(bb)) - Integer.parseInt(sdf_13.format(date))
    }

    fun getSimpleTime(date: Long): String {
        if (date == 0L)
            return ""
        val now = Calendar.getInstance()
        val yet = Calendar.getInstance()
        yet.timeInMillis = date

        val nowDate = dateToInt3(now.timeInMillis)
        //		int nowTime=DateFormat.dateToInt2(now.getTimeInMillis());
        //		int nowHour=DateFormat.getHour(now.getTimeInMillis());
        //		int nowM=DateFormat.getM(now.getTimeInMillis());

        val cdate = dateToInt3(yet.timeInMillis)
        //		int time=DateFormat.dateToInt2(c.getTimeInMillis());
        //		int hour=DateFormat.getHour(c.getTimeInMillis());
        //		int m=DateFormat.getM(c.getTimeInMillis());

        if (nowDate == cdate) {
            return sdf_8.format(yet.timeInMillis)
        } else if (nowDate - cdate == 1) {
            return "昨天 " + sdf_8.format(yet.timeInMillis)
        } else if (nowDate - cdate <= 7) {
            var str = ""
            when (yet.get(Calendar.DAY_OF_WEEK)) {
                0 -> str = "六"
                1 -> str = "日"
                2 ->
                    //					str = "二";
                    str = "一"
                3 ->
                    //					str = "三";
                    str = "二"
                4 ->
                    //					str = "四";
                    str = "三"
                5 ->
                    //					str = "五";
                    str = "四"
                6 ->
                    //					str = "六";
                    str = "五"
                7 -> str = "六"
            }//					str = "日";
            //					str = "一";
            return "星期" + str + " " + sdf_8.format(yet.timeInMillis)
        } else {
            return sdf_12.format(yet.timeInMillis)
        }
    }

    fun getSimpleTime2(date: Long): String {
        if (date == 0L)
            return ""
        val now = Calendar.getInstance()
        val yet = Calendar.getInstance()
        yet.timeInMillis = date

        val nowDate = dateToInt3(now.timeInMillis)
        //		int nowTime=DateFormat.dateToInt2(now.getTimeInMillis());
        //		int nowHour=DateFormat.getHour(now.getTimeInMillis());
        //		int nowM=DateFormat.getM(now.getTimeInMillis());

        val cdate = dateToInt3(yet.timeInMillis)
        //		int time=DateFormat.dateToInt2(c.getTimeInMillis());
        //		int hour=DateFormat.getHour(c.getTimeInMillis());
        //		int m=DateFormat.getM(c.getTimeInMillis());

        if (nowDate == cdate) {
            return sdf_8.format(yet.timeInMillis)
        } else if (nowDate - cdate == 1) {
            return "昨天"
        } else if (nowDate - cdate <= 7) {
            var str = ""
            when (yet.get(Calendar.DAY_OF_WEEK) - 1) {
                0 -> str = "日"
                1 -> str = "一"
                2 -> str = "二"
                3 -> str = "三"
                4 -> str = "四"
                5 -> str = "五"
                6 -> str = "六"
                else -> return sdf_3.format(yet.timeInMillis)
            }
            return "星期$str"
        } else {
            return sdf_3.format(yet.timeInMillis)
        }
    }

    fun getSimpleTime3(date: Long): String {
        if (date == 0L)
            return ""
        val now = Calendar.getInstance()
        val yet = Calendar.getInstance()
        yet.timeInMillis = date

        val nowDate = dateToInt3(now.timeInMillis)
        //		int nowTime=DateFormat.dateToInt2(now.getTimeInMillis());
        //		int nowHour=DateFormat.getHour(now.getTimeInMillis());
        //		int nowM=DateFormat.getM(now.getTimeInMillis());

        val cdate = dateToInt3(yet.timeInMillis)
        //		int time=DateFormat.dateToInt2(c.getTimeInMillis());
        //		int hour=DateFormat.getHour(c.getTimeInMillis());
        //		int m=DateFormat.getM(c.getTimeInMillis());

        if (nowDate == cdate) {
            return "今天"
        } else if (nowDate - cdate == 1) {
            return "昨天"
        } else if (nowDate - cdate <= 7) {
            var str = ""
            when (yet.get(Calendar.DAY_OF_WEEK) - 1) {
                0 -> str = "日"
                1 -> str = "一"
                2 -> str = "二"
                3 -> str = "三"
                4 -> str = "四"
                5 -> str = "五"
                6 -> str = "六"
                else -> return sdf_20.format(yet.timeInMillis)
            }
            return "星期$str"
        } else {
            return sdf_20.format(yet.timeInMillis)
        }
    }

    /**
     * 当年的日期不显示年份，不是当年的日期显示年份
     *
     * @param date
     * @return
     */
    fun getCurrentYearFormatTime(date: Long): String {

        if (date == 0L) {
            return ""
        }
        val now = Calendar.getInstance()
        val yet = Calendar.getInstance()
        yet.timeInMillis = date
        return if (now.get(Calendar.YEAR) == yet.get(Calendar.YEAR)) {
            dateToString(SimpleDateFormat("MM-dd HH:mm"), date)
        } else {
            dateToString(SimpleDateFormat("yyyy-MM-dd"), date)
        }
    }

    fun parsDateModel(time: String): DateModel? {
        try {
            return parsDateModel(java.lang.Long.valueOf(time))
        } catch (e: Exception) {
            return null
        }

    }

    fun parsDateModel(time: Long): DateModel {
        val model = DateModel()
        model.day = time / DATE_TIME
        model.hour = time / HOUR_TIME - model.day * 24
        model.minute = time / MINYTE_TIME - model.day * 24 * 60 - model.hour * 60
        model.second = time / SECOND_TIME - model.day * 24 * 60 * 60 - model.hour * 60 * 60 - model.minute * 60
        return model
    }

    /**
     * 格式化时间，如果同一年，返回格式为12-12
     * 如果跨年返回格式  2016-12-12
     *
     * @param time
     * @return
     */
    fun formatTimeByMdOrYmd(time: Long): String {
        val calendarTime = Calendar.getInstance()
        calendarTime.timeInMillis = time

        val calendarNow = Calendar.getInstance()
        calendarNow.timeInMillis = System.currentTimeMillis()

        return if (calendarTime.get(Calendar.YEAR) == calendarNow.get(Calendar.YEAR)) { // 同一年，返回12-24
            FORMAT_MD.format(Date(time))
        } else FORMAT_YMD.format(Date(time))
// 不同年，返回2016-12-24
    }

    class DateModel {
        var year: Long = 0
        var money: Long = 0
        var day: Long = 0
        var hour: Long = 0
        var minute: Long = 0
        var second: Long = 0

        override fun toString(): String {
            val buffer = "[year=%d,money=%d,day=%d,hour=%d,minute=%d,second=%d]"
            return String.format(buffer, year, money, day, hour, minute, second)
        }
    }

    /**
     * 判断日期是否为今天
     *
     * @param date
     * @return
     */
    fun isToday(date: Long): Boolean {
        val now = Date()
        val thatDate = Date(date)

        val calendarNow = Calendar.getInstance()
        calendarNow.time = now

        val calendarThatDay = Calendar.getInstance()
        calendarThatDay.time = thatDate

        return (calendarNow.get(Calendar.YEAR) == calendarThatDay.get(Calendar.YEAR)
                && calendarNow.get(Calendar.MONTH) == calendarThatDay.get(Calendar.MONTH)
                && calendarNow.get(Calendar.DATE) == calendarThatDay.get(Calendar.DATE))
    }


    /**
     * 判断日期是否为今天
     *
     * @param date
     * @return
     */
    fun isYesterday(date: Long): Boolean {
        val now = Date()
        val thatDate = Date(date)

        val calendarNow = Calendar.getInstance()
        calendarNow.time = now

        val calendarThatDay = Calendar.getInstance()
        calendarThatDay.time = thatDate
        calendarThatDay.roll(Calendar.DATE, true)

        return (calendarNow.get(Calendar.YEAR) == calendarThatDay.get(Calendar.YEAR)
                && calendarNow.get(Calendar.MONTH) == calendarThatDay.get(Calendar.MONTH)
                && calendarNow.get(Calendar.DATE) == calendarThatDay.get(Calendar.DATE))
    }


    /**
     * 判断日期是否为同一天
     *
     * @return -1是去年 0是当天前 1是当天
     */
    fun isSameDay(date: Long): Int {
        val time = Date(date)
        val currentTime = Date(System.currentTimeMillis())

        val nowCalendar = Calendar.getInstance()
        nowCalendar.time = currentTime

        val dateCalendar = Calendar.getInstance()
        dateCalendar.time = time

        if (nowCalendar.get(Calendar.YEAR) == dateCalendar.get(Calendar.YEAR)

                && nowCalendar.get(Calendar.MONTH) == dateCalendar.get(Calendar.MONTH)

                && nowCalendar.get(Calendar.DATE) == dateCalendar.get(Calendar.DATE)) {

            return 1
        } else if (nowCalendar.get(Calendar.YEAR) != dateCalendar.get(Calendar.YEAR)) {
            return -1
        } else if (nowCalendar.get(Calendar.YEAR) == dateCalendar.get(Calendar.YEAR)

                && nowCalendar.get(Calendar.MONTH) == dateCalendar.get(Calendar.MONTH)

                && nowCalendar.get(Calendar.DATE) != dateCalendar.get(Calendar.DATE)) {
            return 0
        }
        return -1
    }


    /**
     * 判断是不是一天以前
     *
     * @param date
     * @return
     */
    fun isOneDayBefore(date: Long): String {
        if (System.currentTimeMillis() - date > 86400000) {
            return dateToString24(date)
        } else if (System.currentTimeMillis() - date in 3600001..86399999) {
            val l = (System.currentTimeMillis() - date) / 1000 / 60 / 60
            return l.toString() + App.getInstance().getString(R.string.hour_before)
        } else {
            return App.getInstance().getString(R.string.just_now)
        }
    }

}
