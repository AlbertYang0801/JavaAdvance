package com.albert.rpc.framework.register;

import java.util.HashMap;
import java.util.Map;

/**
 * 注册中心
 * @author yangjunwei
 * @date 2024/7/25
 */
public class LocalRegister {

    private static Map<String, Class> map = new HashMap<>();

    public static void regist(String interfaceName, Class implClass) {
        map.put(interfaceName, implClass);
    }

    public static Class get(String interfaceName) {
        return map.get(interfaceName);
    }


}
