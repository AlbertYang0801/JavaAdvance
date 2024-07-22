package com.albert.spring.bytebuddy;

import com.albert.spring.aop.bytebuddy.OriginBean;
import com.albert.spring.aop.bytebuddy.PoliteInvocationHandler;
import com.albert.spring.aop.bytebuddy.ProxyResolver;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

/**
 * @author yangjunwei
 * @date 2024/7/22
 */
public class ByteBuddyTest {

    @Test
    public void test() {
        OriginBean originBean = new OriginBean();
        originBean.name = "Albert";
        Assert.assertEquals("Hello Albert .", originBean.hello());

        OriginBean proxy = new ProxyResolver().createProxy(originBean, new PoliteInvocationHandler());
        Assert.assertEquals("Hello Albert !", proxy.hello());
        // 调用不带@Polite的方法
        Assert.assertEquals("Morning Albert .", proxy.morning());
        Assert.assertNotSame(proxy.getClass(), originBean.getClass());

        //代理类的属性为空，只是调用了代理类的方法。原始Bean里面的字段不为空。
        Assert.assertNull(proxy.name);
        Assert.assertNotNull(originBean.name);

    }


}
