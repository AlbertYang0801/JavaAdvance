package com.albert.spring.factorybean;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

/**
 * 继承FactoryBean
 * @author Albert
 * @date 2021/3/10 下午10:48
 */
@Component
public class FactoryBeanDemo implements FactoryBean {
    @Override
    public Object getObject() throws Exception {
        //该类会将getObject()方法返回的对象注入到容器中
        return new FactoryBeanServiceImpl();
    }

    @Override
    public Class<?> getObjectType() {
        return FactoryBeanServiceImpl.class;
    }


}
