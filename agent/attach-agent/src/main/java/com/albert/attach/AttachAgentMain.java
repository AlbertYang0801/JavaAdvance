package com.albert.attach;

import com.sun.tools.attach.VirtualMachine;

/**
 * attach方式加载agent
 *
 * @author yangjunwei
 * @date 2024/7/29
 */
public class AttachAgentMain {

    public static void main(String[] args) throws Exception {
        if (args.length > 1) {
            System.out.println("参数过长");
        }
        Long pid = 0L;
        try {
            pid = Long.parseLong(args[0]);
        } catch (Exception e) {
            System.out.println("请传入pid");
        }
        VirtualMachine vm = null;
        try {
            System.out.println("开始attach");
            //1.根据pid，与目标jvm进程建立Socket连接
            vm = VirtualMachine.attach(pid.toString());
            vm.loadAgent("/lib/agent-practice-0.0.1-SNAPSHOT-jar-with-dependencies.jar");
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
