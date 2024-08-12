package com.albert.rpc.frame.register;

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
     * 扫描注解，自动加载 RpcServerInterface
     */
    @PostConstruct
    public void processAnnotatedBeans() {
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            MyRpcServer myRpcServer = applicationContext.findAnnotationOnBean(beanDefinitionName, MyRpcServer.class);
            if (myRpcServer != null) {
                Class<?> type = applicationContext.getType(beanDefinitionName);
                log.info("register service cache {}:{}", myRpcServer.className(), type.getName());
                //添加远程服务方法到本地缓存
                if (SERVICE_CACHE.containsKey(myRpcServer.className())) {
                    throw new RuntimeException("rpcMethod className Cannot be repeated! className:" + myRpcServer.className());
                }
                SERVICE_CACHE.put(myRpcServer.className(), type);
            }
        }
    }


}
