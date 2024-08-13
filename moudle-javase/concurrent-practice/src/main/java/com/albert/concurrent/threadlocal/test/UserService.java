package com.albert.concurrent.threadlocal.test;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yangjunwei
 * @date 2024/7/3
 */
@Service
public class UserService {

    private static ThreadLocal<List<UserVo>> threadLocal = new ThreadLocal<>();

    public List<UserVo> getUserVoList() {
        if (threadLocal.get() == null) {
            List<UserVo> list = new ArrayList<>();
            for (int i = 0; i < 1000; i++) {
                list.add(UserVo.getTestData());
            }
            threadLocal.set(list);
        }
        return threadLocal.get();
    }

}
