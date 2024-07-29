package com.albert.agent;

import javassist.*;

import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

/**
 * 拦截date类
 * https://juejin.cn/post/7361564628952399899#heading-22
 *
 * @author yangjunwei
 * @date 2024/7/29
 */
public class DefineTransformer implements ClassFileTransformer {

    /**
     * 拦截所有类，只处理自定义类
     * @param loader                the defining loader of the class to be transformed,
     *                              may be <code>null</code> if the bootstrap loader
     * @param className             the name of the class in the internal form of fully
     *                              qualified class and interface names as defined in
     *                              <i>The Java Virtual Machine Specification</i>.
     *                              For example, <code>"java/util/List"</code>.
     * @param classBeingRedefined   if this is triggered by a redefine or retransform,
     *                              the class being redefined or retransformed;
     *                              if this is a class load, <code>null</code>
     * @param protectionDomain      the protection domain of the class being defined or redefined
     * @param classfileBuffer       the input byte buffer in class file format - must not be modified
     *
     * @return
     * @throws IllegalClassFormatException
     */
    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {

        //拦截date类
        if (className.equals("com/albert/javase/agent/util/AgentUtilTest")) {
            CtClass ctClass = null;
            System.out.println("对clas开始打桩");

            final ClassPool classPool = ClassPool.getDefault();

            try {
                ctClass = classPool.get("com.albert.javase.agent.util.AgentUtilTest");
                CtMethod ctMethod = ctClass.getDeclaredMethod("getTime");
                //篡改方法实现
                String methodBody = "{" +
                        "long currentTime = System.currentTimeMillis();" +
                        "System.out.println(\"使用agent探针对Date方法进行修改并打印，当前时间【毫秒级】：+currentTime\");" +
                        "return currentTime/1000;" +
                        "}";
                ctMethod.setBody(methodBody);
                return ctClass.toBytecode();
            } catch (NotFoundException e) {
                throw new RuntimeException(e);
            } catch (CannotCompileException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } finally {
                //调用CtClass对象的detach()方法后，对应class的其他方法将不能被调用。但是，你能够通过ClassPool的get()方法，
                //重新创建一个代表对应类的CtClass对象。如果调用ClassPool的get()方法， ClassPool将重新读取一个类文件，并且重新创建一个CtClass对象，并通过get()方法返回
                //如下所说：
                //detach的意思是将内存中曾经被javassist加载过的Date对象移除，如果下次有需要在内存中找不到会重新走javassist加载
                ctClass.detach();
            }
        }
        return classfileBuffer;
    }


}
