package com.albert.rpc.frame.netty;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author yjw
 * @date 2024/6/10 19:40
 */
public class SecurityCenter {

    //用以检查用户是否重复登录的缓存
    private static Map<String, Boolean> nodeCheck = new ConcurrentHashMap<String, Boolean>();

    //用户登录的白名单
    private static Set<String> whiteList = new CopyOnWriteArraySet<>();

    static{
        whiteList.add("127.0.0.1");
    }

    public static boolean isWhiteIP(String ip){
        return whiteList.contains(ip);
    }

    public static boolean isDupLog(String usrInfo){
        return nodeCheck.containsKey(usrInfo);
    }

    public static void addLoginUser(String usrInfo){
        nodeCheck.put(usrInfo,true);
    }

    public static void removeLoginUser(String usrInfo){
        nodeCheck.remove(usrInfo,true);
    }

}
