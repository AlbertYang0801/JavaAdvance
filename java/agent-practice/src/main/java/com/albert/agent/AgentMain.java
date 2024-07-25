package com.albert.agent;

import java.lang.instrument.Instrumentation;

/**
 * @author yjw
 * @date 2024/6/7 12:24
 */
public class AgentMain {

    // premain方法
    public static void premain(String agentArgs, Instrumentation inst) {
        System.out.println("【remain执行了】");
    }

    // premain方法
    public static void agentmain(String agentArgs, Instrumentation inst) {
        System.out.println("【agentmain执行了】");
    }


}
