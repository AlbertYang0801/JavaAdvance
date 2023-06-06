package com.albert.javase.ip;

import com.albert.javase.guava.bean.TestVo;
import com.albert.javase.guava.bean.UserVo;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yangjunwei
 * @date 2023/2/21 10:43 上午
 */
public class UrlTest {

//    public static void main(String[] args) {
//        URI uri = URI.create("https://arms.console.aliyun.com/alertapi/console/v1/dispatch/newList.json");
//        System.out.println(uri.getScheme());
//        System.out.println(uri.getSchemeSpecificPart());
//        System.out.println(uri.getPath());
//        System.out.println(uri.getRawPath());
//
//        String servletPath = uri.getPath();
//        String subPath = servletPath;
//        if (servletPath.startsWith("/alertapi")) {
//            subPath = subPath.substring("/alertapi".length());
//        }
//        System.out.println(subPath);
//    }

    public static void main(String[] args) {
        List<UserVo> userVos = new ArrayList<>();
        UserVo userVo = new UserVo();
        userVo.setId(1);
        userVo.setName("123");
        userVos.add(userVo);

        TestVo testVo = new TestVo();
        testVo.setList(userVos);

        List<TestVo> testVos = new ArrayList<>();
        testVos.add(testVo);

        TestVo testVo1 = new TestVo();
        testVo1.setList(userVos);
        testVo1.setTest("test");

        testVos.forEach(item ->{
            List<UserVo> list = item.getList();

            for (UserVo vo : list) {
                if(vo.getId()==1){
                    System.out.println(vo.getName());
                    return;
                }
            }
            System.out.println(item.getTest());
        });

    }

}
