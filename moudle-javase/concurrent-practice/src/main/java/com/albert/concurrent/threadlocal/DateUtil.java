package com.albert.concurrent.threadlocal;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author yangjunwei
 * @date 2024/7/3
 */
public class DateUtil {

    //使用ThreadLocal封装，解决相同变量访问冲突的问题
    //如果不手动remove，是否发生内存泄漏
    static ThreadLocal<SimpleDateFormat> threadLocal = new ThreadLocal<>();

    private static SimpleDateFormat getSimpleDateFormat() {
        if (threadLocal.get() == null) {
            threadLocal.set(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        }
        return threadLocal.get();
    }

    /**
     * 解析方法
     *
     * @param date
     * @return
     */
    public static String parse(Date date) {
        return getSimpleDateFormat().format(date);
    }




}
