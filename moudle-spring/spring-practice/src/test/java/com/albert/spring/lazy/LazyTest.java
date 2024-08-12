package com.albert.spring.lazy;

import cn.hutool.extra.spring.SpringUtil;
import com.albert.spring.AbstractBaseTest;
import com.albert.spring.dependent.lazy.ClassB;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

/**
 * @author yangjunwei
 * @date 2024/7/25
 */
public class LazyTest extends AbstractBaseTest {

    @Test
    public void testClassB() {
        Assert.assertEquals(SpringUtil.getBean(ClassB.class).helloA(),"hello");
    }


}
