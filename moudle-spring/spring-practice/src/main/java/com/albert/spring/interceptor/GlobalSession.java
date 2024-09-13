package com.albert.spring.interceptor;

import org.springframework.stereotype.Component;

@Component
public class GlobalSession {

    private static ThreadLocal<String> USER_NAME = new ThreadLocal<>();

    public static void setUserName(String userName) {
        GlobalSession.USER_NAME.set(userName);
    }

    public static String getUserName() {
        return GlobalSession.USER_NAME.get();
    }

    public static void clear() {
        GlobalSession.USER_NAME.remove();
    }

}
