package com.albert.concurrent.threadlocal.test;

import com.albert.concurrent.threadlocal.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 测试threadlocal内存泄漏
 *
 * @author yangjunwei
 * @date 2024/7/3
 */
@RestController
public class TestController {

    @Autowired
    UserService userService;

    @GetMapping("/test")
    public void test() {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 100; i++) {
            //对象太小，测不出来
            executorService.submit(() -> DateUtil.parse(new Date()));
        }
    }

    private static ExecutorService executorService = Executors.newFixedThreadPool(500);

    @GetMapping("/object")
    public void testObject() {
        for (int i = 0; i < 100; i++) {
            executorService.submit(() -> {
                //对象列表更明显
                List<UserVo> userVoList = userService.getUserVoList();
                System.out.println(userVoList.size());
            });
        }
    }


}
