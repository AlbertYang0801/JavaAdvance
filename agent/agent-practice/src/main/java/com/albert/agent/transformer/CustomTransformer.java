package com.albert.agent.transformer;

import com.albert.agent.advice.AdviceFactory;
import javassist.*;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

/**
 * 自定义类改造器
 *
 * @author yangjunwei
 * @date 2024/7/30
 */
@Slf4j
public class CustomTransformer implements ClassFileTransformer {

    /**
     * 拦截所有类，只处理自定义类
     *
     * @param loader              the defining loader of the class to be transformed,
     *                            may be <code>null</code> if the bootstrap loader
     * @param className           the name of the class in the internal form of fully
     *                            qualified class and interface names as defined in
     *                            <i>The Java Virtual Machine Specification</i>.
     *                            For example, <code>"java/util/List"</code>.
     * @param classBeingRedefined if this is triggered by a redefine or retransform,
     *                            the class being redefined or retransformed;
     *                            if this is a class load, <code>null</code>
     * @param protectionDomain    the protection domain of the class being defined or redefined
     * @param classfileBuffer     the input byte buffer in class file format - must not be modified
     * @return
     * @throws IllegalClassFormatException
     */
    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        //匹配Class
        String finalClassName = className.replace("/", ".");
        if (AdviceFactory.matchClass(finalClassName)) {
            //匹配方法
            log.info("匹配成功，className:{}", finalClassName);
            return matchMethod(finalClassName, classfileBuffer);
        }
        return classfileBuffer;
    }

    private byte[] matchMethod(String className, byte[] classfileBuffer) {
        final ClassPool classPool = ClassPool.getDefault();
        CtClass ctClass = null;
        try {
            ctClass = classPool.get(className);
            for (CtMethod method : ctClass.getMethods()) {
                log.info("匹配到的methodName:{}", method.getName());

                //匹配到方法
                if (AdviceFactory.matchClassAndMethod(className, method.getName())) {
                    //增强方法
                    modifyMethod(ctClass, method);
                    return ctClass.toBytecode();
                }
            }
            return classfileBuffer;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            //调用CtClass对象的detach()方法后，对应class的其他方法将不能被调用。但是，你能够通过ClassPool的get()方法，
            //重新创建一个代表对应类的CtClass对象。如果调用ClassPool的get()方法， ClassPool将重新读取一个类文件，并且重新创建一个CtClass对象，并通过get()方法返回
            //如下所说：
            //detach的意思是将内存中曾经被javassist加载过的Date对象移除，如果下次有需要在内存中找不到会重新走javassist加载
            ctClass.detach();
        }
    }

    private void modifyMethod(CtClass ctClass, CtMethod ctMethod) throws CannotCompileException, IOException {
        // 在方法调用前插入代码
        String insertBeforeContent = AdviceFactory.insertBeforeContent(ctClass.getName(), ctMethod.getName(), getParameterLength(ctMethod));

        log.info("insertBeforeContent:{}", insertBeforeContent);
        ctMethod.insertBefore(insertBeforeContent);

        //ctMethod.insertBefore("{ System.out.println(\"Before myMethod\"); }");

        ctClass.writeFile("build/classes");
    }

    private int getParameterLength(CtMethod ctMethod) {
        try {
            return ctMethod.getParameterTypes().length;
        } catch (NotFoundException e) {
            return 0;
        }
    }


}