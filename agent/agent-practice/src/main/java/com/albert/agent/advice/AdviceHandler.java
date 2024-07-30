package com.albert.agent.advice;

import com.albert.agent.advice.annotation.AdvicePointCut;
import com.albert.agent.io.ResourceResolver;
import com.albert.agent.utils.ClassUtils;

import java.lang.reflect.Modifier;
import java.util.*;

/**
 * 处理切面
 *
 * @author yangjunwei
 * @date 2024/7/30
 */
public class AdviceHandler {

    public void scanAdvice(Class configClass) {
        //扫描所有类
        Set<String> classNameSet = scanForClassNames(configClass);
        //反射获取切面定义
        Map<AdvicePointCut, Class<IAdvice>> adviceDefs = createAdviceDefs(classNameSet);
        //注册切面
        adviceDefs.forEach(this::registerAdvicePointCut);
    }

    /**
     * 扫面所有类
     *
     * @param configClass
     * @return className
     */
    protected Set<String> scanForClassNames(Class<?> configClass) {
        String[] scanPackages = new String[]{configClass.getPackage().getName()};
        Set<String> classNameSet = new HashSet<>();
        //扫描pkg下所有的类
        for (String scanPackage : scanPackages) {
            ResourceResolver rr = new ResourceResolver(scanPackage);
            List<String> classList = rr.scan(res -> {
                String name = res.getName();
                if (name.endsWith(".class")) {
                    return name.substring(0, name.length() - 6).replace("/", ".").replace("\\", ".");
                }
                return null;
            });
            classNameSet.addAll(classList);
        }
        return classNameSet;
    }

    /**
     * 根据扫描的类路径反射获取@AdvicePointCut注解
     */
    Map<AdvicePointCut, Class<IAdvice>> createAdviceDefs(Set<String> classNameSet) {
        Map<AdvicePointCut, Class<IAdvice>> adviceDefs = new HashMap<>();
        for (String className : classNameSet) {
            // 反射获取Class
            Class<?> clazz = null;
            try {
                clazz = Class.forName(className);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            //Annotation、enum、interface、record不需要加载
            if (clazz.isAnnotation() || clazz.isEnum() || clazz.isInterface()) {
                continue;
            }
            // 是否标注@Component?
            AdvicePointCut advicePointCut = ClassUtils.findAnnotation(clazz, AdvicePointCut.class);
            if (advicePointCut != null) {
                int mod = clazz.getModifiers();
                if (Modifier.isAbstract(mod)) {
                    throw new RuntimeException("@Component class " + clazz.getName() + " must not be abstract.");
                }
                if (Modifier.isPrivate(mod)) {
                    throw new RuntimeException("@Component class " + clazz.getName() + " must not be private.");
                }
                adviceDefs.put(advicePointCut, (Class<IAdvice>) clazz);
            }
        }
        return adviceDefs;
    }

    /**
     * 注册切面
     * @param advicePointCut
     * @param adviceClass
     */
    private void registerAdvicePointCut(AdvicePointCut advicePointCut, Class<IAdvice> adviceClass) {
        AdviceFactory.registerAdvicePointCut(advicePointCut, adviceClass);
    }


}
