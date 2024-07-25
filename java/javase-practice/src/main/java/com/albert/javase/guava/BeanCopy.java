package com.albert.javase.guava;

import cn.hutool.core.bean.BeanUtil;
import com.albert.javase.guava.bean.UserDo;
import com.albert.javase.guava.bean.UserVo;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.List;

/**
 * @author yangjunwei
 * @date 2022/4/14 2:57 下午
 */
public class BeanCopy {

    @Test
    public void testListCopy(){
       List<UserDo> list = Lists.newArrayList();
        UserDo userA = UserDo.builder().id(1).name("1").build();
        UserDo userB = UserDo.builder().id(2).name("2").build();
        list.add(userA);
        list.add(userB);

        List<UserVo> userVos = BeanUtil.copyToList(list, UserVo.class, null);
        System.out.println(JSON.toJSONString(userVos));
    }
    

}
