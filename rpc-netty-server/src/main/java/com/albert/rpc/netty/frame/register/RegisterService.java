package com.albert.rpc.netty.frame.register;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 注册服务到本地缓存
 *
 * @author yangjunwei
 * @date 2024-06-11
 */
@Component
@Slf4j
public class RegisterService {

    @Autowired
    private ApplicationContext applicationContext;

    private static final Map<String, Class> SERVICE_CACHE = new ConcurrentHashMap<>();

    /**
     * 注册本地服务
     *
     * @param serviceName
     * @param implClass
     */
    public void regRemote(String serviceName, Class implClass) {
        //服务放入本地缓存
        SERVICE_CACHE.put(serviceName, implClass);
    }

    /**
     * 获取本地服务
     *
     * @param serviceName
     * @return
     */
    public Class getLocalService(String serviceName) {
        return SERVICE_CACHE.get(serviceName);
    }

    /**
     * 扫描注解
     */
    @PostConstruct
    public void processAnnotatedBeans() {
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            MyRpcServerInterface myRpcServerInterface = applicationContext.findAnnotationOnBean(beanDefinitionName, MyRpcServerInterface.class);
            if (myRpcServerInterface != null) {
                Class<?> type = applicationContext.getType(beanDefinitionName);
                log.info("register service cache {}:{}", myRpcServerInterface.className(), type.getName());
                //添加远程服务方法到本地缓存
                if (SERVICE_CACHE.containsKey(myRpcServerInterface.className())) {
                    throw new RuntimeException("rpcMethod className Cannot be repeated! className:" + myRpcServerInterface.className());
                }
                SERVICE_CACHE.put(myRpcServerInterface.className(), type);
            }
        }
    }


}
