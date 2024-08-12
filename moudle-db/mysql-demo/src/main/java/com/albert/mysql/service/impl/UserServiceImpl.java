package com.albert.mysql.service.impl;

import com.albert.mysql.mapper.UserMapper;
import com.albert.mysql.model.entity.UserDo;
import com.albert.mysql.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author yangjunwei
 * @date 2024/8/6
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserDo> implements IUserService {

    @Override
    public void insertUserNoException(String name) {
        System.out.println("save user name" + name);
        this.save(new UserDo().build(name));
    }

    @Override
    public void insertUserException(String name) {
        System.out.println("save user name" + name);
        this.save(new UserDo().build(name));
        int i = 10 / 0;
    }


}
