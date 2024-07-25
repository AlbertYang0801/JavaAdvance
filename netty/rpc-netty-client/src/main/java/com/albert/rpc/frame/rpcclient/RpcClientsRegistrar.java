package com.albert.rpc.frame.rpcclient;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;

import java.lang.reflect.Proxy;
import java.util.HashSet;
import java.util.Set;

/**
 * 使用 ImportBeanDefinitionRegistrar 扫描加在接口上的自定义注解 @RpcClient
 * 接口未被 Spring 管理
 *
 * @author yangjunwei
 * @date 2024-06-11
 */
@Component
@Slf4j
public class RpcClientsRegistrar implements ImportBeanDefinitionRegistrar, ResourceLoaderAware, EnvironmentAware {

    private ResourceLoader resourceLoader;

    private Environment environment;

    @Autowired
    ApplicationContext applicationContext;

    /**
     * 实现ImportBeanDefinitionRegistrar的方法，在配置类 import 时生效
     *
     * @param importingClassMetadata
     * @param registry
     * @param importBeanNameGenerator
     */
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry, BeanNameGenerator importBeanNameGenerator) {
        registerRpcClients(importingClassMetadata, registry);
    }

    public void registerRpcClients(AnnotationMetadata metadata, BeanDefinitionRegistry registry) {
        //资源加载器，扫描包
        ClassPathScanningCandidateComponentProvider scanner = getScanner();
        scanner.setResourceLoader(this.resourceLoader);
        //扫描类路径
        Set<String> basePackages = getBasePackages(metadata);
        //扫描指定注解
        AnnotationTypeFilter annotationTypeFilter = new AnnotationTypeFilter(MyRpcClient.class);
        scanner.addIncludeFilter(annotationTypeFilter);

        for (String basePackage : basePackages) {
            //获取当前包下符合条件的BeanDefinition
            Set<BeanDefinition> candidateComponents = scanner.findCandidateComponents(basePackage);
            for (BeanDefinition bean : candidateComponents) {
                try {
                    //获取接口Class
                    Class<?> clazz = Class.forName(bean.getBeanClassName());
                    //必须是接口加 MyRpcClient 注解
                    if (clazz.isInterface() && clazz.isAnnotationPresent(MyRpcClient.class)) {
                        // 获取注解
                        MyRpcClient annotation = clazz.getAnnotation(MyRpcClient.class);
                        log.info(" MyRpcClient 接口名 {} 加载进来 {}", clazz.getName(), annotation.value());
                        //加到 Spring 容器
                        BeanDefinitionBuilder beanDefinitionBuilder =
                                //动态代理
                                BeanDefinitionBuilder.genericBeanDefinition(clazz, () -> getRemoteProxyObject(clazz, annotation.value()));
                        beanDefinitionBuilder.setAutowireMode(AbstractBeanDefinition.AUTOWIRE_BY_TYPE);
                        BeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();
                        //注册单例bean
                        registry.registerBeanDefinition(annotation.value(), beanDefinition);
                    }
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    protected ClassPathScanningCandidateComponentProvider getScanner() {
        return new ClassPathScanningCandidateComponentProvider(false, this.environment) {
            @Override
            protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
                boolean isCandidate = false;
                if (beanDefinition.getMetadata().isIndependent()) {
                    if (!beanDefinition.getMetadata().isAnnotation()) {
                        isCandidate = true;
                    }
                }
                return isCandidate;
            }
        };
    }

    protected Set<String> getBasePackages(AnnotationMetadata importingClassMetadata) {
        Set<String> basePackages = new HashSet<>();
        //EnableRpcClients 类路径
        basePackages.add(ClassUtils.getPackageName(importingClassMetadata.getClassName()));
        return basePackages;
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }


    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    /**
     * 对接口进行动态代理
     * 最终调用实现了 InvocationHandler 的 DynProxy 类
     *
     * @param serviceInterface
     * @param <T>
     * @return
     */
    public <T> T getRemoteProxyObject(final Class<?> serviceInterface, String annotationValue) {
        return (T) Proxy.newProxyInstance(serviceInterface.getClassLoader(), new Class<?>[]{serviceInterface},
                new DynProxy(annotationValue));
    }


}
