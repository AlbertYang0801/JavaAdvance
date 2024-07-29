package com.albert.agent;

import com.albert.agent.attach.AttachDefineTransformer;

import java.lang.instrument.Instrumentation;

/**
 * @author yjw
 * @date 2024/6/7 12:24
 */
public class AgentMain {

    /**
     * 静态加载
     * -javaagent:/Users/admin/IdeaProjects/JavaAdvance/java/agent-practice/target/agent-practice-0.0.1-SNAPSHOT-jar-with-dependencies.jar
     * @param agentArgs
     * @param inst
     */
    public static void premain(String agentArgs, Instrumentation inst) {
        System.out.println("【remain执行了】");
        //添加拦截类
        inst.addTransformer(new DefineTransformer(), true);
    }

    // premain方法
    public static void agentmain(String agentArgs, Instrumentation inst) {
        System.out.println("【agentmain执行了】");
        inst.addTransformer(new AttachDefineTransformer(), true);
    }


}
