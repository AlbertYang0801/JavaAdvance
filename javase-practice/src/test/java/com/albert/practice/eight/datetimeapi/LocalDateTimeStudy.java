package com.albert.practice.eight.datetimeapi;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;

/**
 * java8新特性
 * Date-Time API 练习
 * @author Albert
 * @date 2020/8/4 09:52
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Slf4j
public class LocalDateTimeStudy {

    /**
     * 本地化日期时间API
     * 包含LocalDateTime、LocalDate、LocalTime
     */
    @Test
    public void testLocalDateTime(){

        LocalDateTime now = LocalDateTime.now();
        System.out.println("当前日期+时间： "+now);

        String nowString = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.println("格式化当前日期+时间： "+nowString);

        LocalDate localDate = now.toLocalDate();
        System.out.println("当前日期： "+localDate);

        LocalTime localTime = now.toLocalTime();
        System.out.println("当前时间： "+localTime);

        int monthValue = now.getMonthValue();
        System.out.println("当前月份： "+monthValue);

        int dayOfMonth = now.getDayOfMonth();
        System.out.println("当前月的第几天： "+dayOfMonth);

        LocalDateTime localDateTime = now.withDayOfMonth(10).withYear(2008).withMonth(06);
        System.out.println("自定义修改当前时间： "+localDateTime);

        LocalDate date = LocalDate.of(2014, Month.APRIL, 12);
        System.out.println("自定义日期: "+date);

        LocalTime time = LocalTime.of(22, 10);
        System.out.println("自定义时间： "+time);

        LocalTime parse = LocalTime.parse("10:10:10");
        System.out.println("解析字符串转换为时间： "+parse);

    }



}



