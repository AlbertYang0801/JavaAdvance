package com.albert.spring.aop.jdk;

import org.springframework.stereotype.Service;

/**
 * @author yjw
 * @date 2024/3/28 1:04
 */
@Service
public class UserServiceImpl implements UserService{
    @Override
    public void createUser() {
        System.out.println("创建用户");
    }
}
