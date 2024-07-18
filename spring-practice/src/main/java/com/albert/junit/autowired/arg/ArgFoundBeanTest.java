package com.albert.junit.autowired.arg;

import com.albert.junit.validator.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * 入参Bean测试
 *
 * @author yangjunwei
 * @date 2024/7/17
 */
@Configuration
public class ArgFoundBeanTest {

    @Bean
    public User userA() {
        User user = new User();
        return user;
    }

    @Bean
    @Primary
    public User userB() {
        User user = new User();
        return user;
    }

    @Bean
    public UserInfo UserInfo(@Autowired User user) {
        return new UserInfo();
    }


}
