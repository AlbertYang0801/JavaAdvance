package com.albert.eightfeatures.localdatetime;

import com.albert.eightfeatures.utils.LocalDateTimeUtils;
import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.List;

/**
 * LocalDateTimeUtils方法的练习
 *
 * @author Albert
 * @date 2020/8/4 16:01
 */
public class TestLocalDateTimeUtils {

    /**
     * 获取当日凌晨的时间和时间戳
     */
    @Test
    public void testWeeHour() {
        LocalDateTime weeHour = LocalDateTimeUtils.getWeeHour();
        System.out.println("今天凌晨对应的时间为：" + LocalDateTimeUtils.formatDateTime(weeHour));
        long weeHourSecend = LocalDateTimeUtils.getWeeHourSecend();
        System.out.println("今日凌晨对应的s时间戳为：" + weeHourSecend);
    }

    /**
     * 获取时间对应的时间戳（ms）
     */
    @Test
    public void testMsTimestamp() {
        LocalDateTime now = LocalDateTime.now();
        Long nowTimeStamp = LocalDateTimeUtils.getMilliByTime(now);
        System.out.println("当前时间对应的ms时间戳为：" + nowTimeStamp);
    }

    /**
     * 获取时间对应的时间戳（s）
     */
    @Test
    public void testSTimestamp() {
        LocalDateTime now = LocalDateTime.now();
        Long nowTimeStamp = LocalDateTimeUtils.getSecondsByTime(now);
        System.out.println("当前时间对应的s时间戳为：" + nowTimeStamp);
    }

    /**
     * 获取小时的集合
     * ChronoUnit.HOURS 单位小时
     * ChronoUnit是一个关于时间单位的枚举类
     */
    @Test
    public void testHourList() {
        //获取指定时间到当前时间之间的小时列表(不包含当前小时)
        List<String> hourList = LocalDateTimeUtils.getTimeList(LocalDateTime.now().withHour(10), ChronoUnit.HOURS, "HH");
        //[10, 11, 12, 13, 14, 15]
        System.out.println(hourList);
    }

    /**
     * 获取包含当前小时的集合
     */
    @Test
    public void testHourListContainNow() {
        //获取指定时间到当前时间之间的小时列表(包含当前小时)
        List<String> hourList = LocalDateTimeUtils.getTimeList(LocalDateTime.now().withHour(10), ChronoUnit.HOURS, "yyyy-MM-dd HH:00", true);
        //[10, 11, 12, 13, 14, 15, 16]
        System.out.println(hourList);
    }

    /**
     * 获取包含当前小时的集合
     */
    @Test
    public void testHourListbetweenTwoTime() {
        //获取指定时间到当前时间之间的小时列表(包含当前小时)
        List<String> hourList = LocalDateTimeUtils.getTimeList(LocalDateTime.now().withHour(10), ChronoUnit.HOURS, "yyyy-MM-dd HH:mm:ss", true);
        //[10, 11, 12, 13, 14, 15, 16]
        System.out.println(hourList);
    }


    /**
     * 获取本月的日期集合
     */
    @Test
    public void testGetMonthDayList() {
        //当月第一天
        LocalDateTime date = LocalDateTime.now().withDayOfMonth(1);
        System.out.println(date);
        List<String> minuteList = LocalDateTimeUtils.getTimeList(date, ChronoUnit.DAYS, "yyyy-MM-dd", true);
        //[2020-08-01, 2020-08-02, 2020-08-03, 2020-08-04, 2020-08-05, 2020-08-06, 2020-08-07]
        System.out.println(minuteList);
    }

    /**
     * 获取指定日期到今天的的日期集合
     */
    @Test
    public void testGetMoreMonthDayList() {
        //设置开始时间为5个月前的第一天
        LocalDateTime date = LocalDateTime.now().minusMonths(5).withDayOfMonth(1);
        System.out.println(date);
        List<String> minuteList = LocalDateTimeUtils.getTimeList(date, ChronoUnit.DAYS, "yyyy-MM-dd", true);
        //[2020-07-01, 2020-07-02, 2020-07-03, 2020-07-04, 2020-07-05]
        System.out.println(minuteList);
    }

    @Test
    public void testGetMoreMonthList() {
        //设置开始时间为5个月前的第一天
        LocalDateTime date = LocalDateTime.now().minusMonths(5).withDayOfMonth(1);
        System.out.println(date);
        List<String> minuteList = LocalDateTimeUtils.getTimeList(date, ChronoUnit.MONTHS, "yyyy-MM-dd", false);
        //[2020-03-01, 2020-04-01, 2020-05-01, 2020-06-01, 2020-07-01]
        System.out.println(minuteList);
    }

    /**
     * 获取指定时间到当前时间的分钟集合(包含当前分钟)
     */
    @Test
    public void testMinuteContainNow() {
        List<String> minuteList = LocalDateTimeUtils.getTimeList(LocalDateTime.now().withMinute(05), ChronoUnit.MINUTES, "mm", true);
        //[05, 06, 07, 08, 09, 10]
        System.out.println(minuteList);
    }

    /**
     * 测试获取当前时间之前的n个小时整点的时间戳（s）
     */
    @Test
    public void testGetOclockAgo() {
        long oClockAgo = LocalDateTimeUtils.getOClockAgo(3);
        System.out.println(oClockAgo);
    }

    /**
     * 测试获取当月第一天
     */
    @Test
    public void testGetFirstDayOfMonth() {
        Temporal firstDay = LocalDateTimeUtils.getFirstDayOfMonth(LocalDateTime.now());
        System.out.println(firstDay);
    }

    /**
     * 获取当月第一天的凌晨
     * 结果是时间戳
     */
    @Test
    public void testGetFirstDayOfCurrentMonth() {
        Long firstDayOfCurrentMonth = LocalDateTimeUtils.getFirstDayOfCurrentMonth();
        System.out.println(firstDayOfCurrentMonth);
    }

    /**
     * 获取两个日期的差值,(根据ChronoUnit枚举的值进行返回对应的单位数值)
     */
    @Test
    public void testBetweenTwoTime() {
        LocalDateTime startTime = LocalDateTime.of(2020, 9, 1, 0, 0, 0);
        LocalDateTime endTime = LocalDateTime.now();
        long hours = LocalDateTimeUtils.betweenTwoTime(startTime, endTime, ChronoUnit.HOURS);
        System.out.println(hours);
    }

    /**
     * 测试两个指定时间的时间戳列表
     */
    @Test
    public void testBetweenLocalDateTime(){
        LocalDateTime startTime = LocalDateTime.of(2020, 9, 2, 15, 0, 0);
        LocalDateTime endTime = LocalDateTime.now();
        List<String> timeList = LocalDateTimeUtils.getTimeList(startTime, endTime, ChronoUnit.HOURS,"yyyy-MM-dd HH:00");
        System.out.println(timeList);
    }

    /**
     * 测试两个指定时间的时间戳列表
     */
    @Test
    public void testBetweenTwoTimeTimestamp(){
        LocalDateTime startTime = LocalDateTime.of(2020, 9, 2, 15, 0, 0);
        LocalDateTime endTime = LocalDateTime.now();
        List<Long> timestampBetweenTwoTime = LocalDateTimeUtils.getTimestampBetweenTwoTime(startTime, endTime, ChronoUnit.HOURS);
        System.out.println(timestampBetweenTwoTime);
    }

    /**
     * 测试日期加上一个数，具体加的单位根据ChronoUnit枚举的值进行对应
     */
    @Test
    public void testPlus() {
        LocalDateTime startTime = LocalDateTime.of(2020, 8, 1, 0, 0, 0);
        LocalDateTime plus = LocalDateTimeUtils.plus(startTime, 10, ChronoUnit.HOURS);
        System.out.println(plus);
    }

    /**
     * 测试分钟加上一个数字
     */
    @Test
    public void testPlusMin() {
        LocalDateTime startTime = LocalDateTime.of(2020, 8, 1, 0, 10, 0);
        LocalDateTime plus = LocalDateTimeUtils.plus(startTime, -5, ChronoUnit.MINUTES);
        System.out.println(plus);
    }

    /**
     * 测试日期减上一个数，具体减的单位根据ChronoUnit枚举的值进行对应
     */
    @Test
    public void testMinu() {
        LocalDateTime startTime = LocalDateTime.of(2020, 8, 1, 0, 0, 0);
        LocalDateTime minu = LocalDateTimeUtils.minu(startTime, 10, ChronoUnit.HOURS);
        System.out.println(minu);
    }

    /**
     * 测试日期格式化
     */
    @Test
    public void testFormat() {
        LocalDateTime startTime = LocalDateTime.of(2020, 8, 1, 0, 0, 0);
        String time = LocalDateTimeUtils.formatDateTime(startTime);
        System.out.println(time);
        String now = LocalDateTimeUtils.formatNow("yyyy-MM-dd");
        System.out.println(now);
    }

    /**
     * 解析指定格式的时间
     */
    @Test
    public void testParseTime() {
        String str = "1986-04-08 12:30";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(str, formatter);
        System.out.println(dateTime);

        String time = "1986-04-08 10:09:10";
        LocalDateTime localDateTime = LocalDateTimeUtils.parseTime(time, "yyyy-MM-dd HH:mm:ss");
        System.out.println(localDateTime);

        String strTime = "2020-03-01";
        LocalDate localDate = LocalDateTimeUtils.parseLocalDate(strTime, "yyyy-MM-dd");
        System.out.println(localDate);

        LocalDateTime of = LocalDateTime.of(localDate, LocalTime.MIN);
        System.out.println(of);

    }

    /**
     * 判断给定时间是否不在当前小时
     */
    @Test
    public void testJudgePassedTimeLine() {
        boolean b = LocalDateTimeUtils.judgePassedTimeLine(10);
        System.out.println(b);
    }

    /**
     * 将时间戳转换为LocalDateTime
     */
    @Test
    public void testParseTimestamp(){
        long time = 1596211200000L;
        LocalDateTime localDateTime = Instant.ofEpochMilli(time).atZone(ZoneOffset.ofHours(8)).toLocalDateTime();
        System.out.println(localDateTime);
        String s = LocalDateTimeUtils.formatTime(localDateTime, "yyyy-MM-dd HH:mm:ss");
        System.out.println(s);
    }

    /**
     * 将时间戳转换为指定日期格式字符串
     */
    @Test
    public void testParseTimestampString(){
        long time = 1596211200000L;
        String timeStr = LocalDateTimeUtils.parseTimestamp(time, LocalDateTimeUtils.DATE_TIME);
        System.out.println(timeStr);
    }



}
