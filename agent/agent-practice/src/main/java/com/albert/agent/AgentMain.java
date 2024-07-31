package com.albert.agent;

import com.albert.agent.advice.AdviceHandler;
import com.albert.agent.transformer.CustomTransformer;
import lombok.extern.slf4j.Slf4j;

import java.lang.instrument.Instrumentation;

/**
 * @author yjw
 * @date 2024/6/7 12:24
 */
@Slf4j
public class AgentMain {

    /**
     * 静态加载
     * -javaagent:/Users/admin/IdeaProjects/JavaAdvance/agent/agent-practice/target/agent-practice-0.0.1-SNAPSHOT-jar-with-dependencies.jar
     *
     * @param agentArgs
     * @param inst
     */
    public static void premain(String agentArgs, Instrumentation inst) {
        System.out.println("【remain执行了】");
        //添加拦截类
        init(inst);
    }

    /**
     * attach模式，根据pid与jvm通信
     * @param agentArgs
     * @param inst
     */
    public static void agentmain(String agentArgs, Instrumentation inst) {
        System.out.println("【agentmain执行了】");
        init(inst);
    }

    public static void init(Instrumentation inst) {
        //扫描切面
        new AdviceHandler().scanAdvice(AgentMain.class);
        //进行类改造
        inst.addTransformer(new CustomTransformer(), true);
    }


}
