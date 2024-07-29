package com.albert.agent.attach;

import com.sun.tools.attach.VirtualMachine;

/**
 * attach方式加载agent
 *
 * @author yangjunwei
 * @date 2024/7/29
 */
public class AttachAgentTest {

    public static void main(String[] args) throws Exception {
        String pid = "66820";
        VirtualMachine vm = null;
        try {
            System.out.println("开始attach");
            //1.根据pid，与目标jvm进程建立Socket连接
            vm = VirtualMachine.attach(pid);
            vm.loadAgent("/Users/admin/IdeaProjects/JavaAdvance/java/agent-practice/target/agent-practice-0.0.1-SNAPSHOT-jar-with-dependencies.jar");
            System.out.println("attach success");
        } finally {
            //程序结束时，卸载agent
            if (vm != null) {
                vm.detach();
            }
        }
        Thread.sleep(20000);
    }

}
