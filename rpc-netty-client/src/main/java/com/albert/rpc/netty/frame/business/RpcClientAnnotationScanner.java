package com.albert.rpc.netty.frame.business;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.type.filter.AssignableTypeFilter;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.function.Supplier;

/**
 * 使用 ApplicationListener 扫描加在接口上的自定义注解
 * 接口未被 Spring 管理
 *
 * @author yangjunwei
 * @date 2024-06-11
 */
@Component
@Slf4j
public class RpcClientAnnotationScanner implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    RemoteInterfaceProxy remoteInterfaceProxy;
    @Autowired
    ApplicationContext applicationContext;

    private String basePackage = "com.albert.rpc.netty.remote.service";

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        //根据 packagePath 扫描注解
        ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(false);
        provider.addIncludeFilter(new AssignableTypeFilter(MyRpcClientInterface.class));

        //packagePath
        Set<BeanDefinition> beans = provider.findCandidateComponents(basePackage);
        for (BeanDefinition bean : beans) {
            try {
                //获取接口
                Class<?> clazz = Class.forName(bean.getBeanClassName());
                if (clazz.isInterface() && clazz.isAnnotationPresent(MyRpcClientInterface.class)) {
                    // 处理注解逻辑
                    MyRpcClientInterface annotation = clazz.getAnnotation(MyRpcClientInterface.class);

                    log.info("接口名 {} 加载进来 {}", clazz.getName(), annotation.className());
                    //加到 Spring 容器
                    if (applicationContext != null) {
                        BeanDefinitionBuilder beanDefinitionBuilder =
                                //生成代理类
                                BeanDefinitionBuilder.genericBeanDefinition(clazz,  () -> remoteInterfaceProxy.getRemoteProxyObject(clazz));
                        BeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();
                        ConfigurableListableBeanFactory beanFactory = ((ConfigurableApplicationContext) applicationContext).getBeanFactory();
                        //注册单例bean
                        beanFactory.registerSingleton(clazz.getName(), beanDefinition);
                    }


                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }


}
