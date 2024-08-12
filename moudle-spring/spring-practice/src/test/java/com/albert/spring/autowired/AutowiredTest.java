package com.albert.spring.autowired;

import cn.hutool.extra.spring.SpringUtil;
import cn.hutool.json.JSONUtil;
import com.albert.spring.AbstractBaseTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author yangjunwei
 * @date 2024/7/17
 */
public class AutowiredTest extends AbstractBaseTest {

    /**
     * 按照类型查找Bean
     * 先按照类型查找Bean，如果找到多个相同类型的Bean，匹配BeanName。如果BeanName匹配不对，则提示Bean匹配异常
     */
    //@Autowired
    //HelloB helloB;

    /**
     * 按照类型查找Bean
     * 先按照类型查找Bean，如果找到多个相同类型的Bean，按照Qualifier配置的name匹配BeanName。
     */
    @Autowired
    @Qualifier("helloResourceB")
    HelloB helloB;

    /**
     * 按照名称查找Bean
     * 没有名称则按照类型找Bean，然后匹配BeanName
     */
    //@Resource(name = "helloB")
    //@Resource
    //HelloB helloB;
    @Test
    public void sayHelloTest() {
        String[] beanDefinitionNames = SpringUtil.getApplicationContext().getBeanDefinitionNames();
        List<String> hellos = Arrays.stream(beanDefinitionNames).filter(beanDefinitionName -> beanDefinitionName.contains("hello")).collect(Collectors.toList());
        System.out.println(JSONUtil.toJsonStr(hellos));
        System.out.println(helloB.getName());
    }


}
