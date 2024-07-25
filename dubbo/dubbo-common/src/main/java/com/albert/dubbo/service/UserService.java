package com.albert.dubbo.service;

import com.albert.dubbo.bean.User;

/**
 * @author yangjunwei
 * @date 2024/7/25
 */
public interface UserService {

    User getUser(String uid);

}
