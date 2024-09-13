package com.albert.spring.controller;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yangjunwei
 * @date 2024/8/26
 */
@RestController
@RequestMapping("/json")
public class JsonController {

    @GetMapping("/user")
    public UserListVo test(){
        UserListVo userListVo = new UserListVo();
        UserVo userVo1 = new UserVo();
        userVo1.setId(1);
        userVo1.setName("111");

        UserVo userVo2 = new UserVo();
        userVo2.setId(1);
        userVo2.setName("111");

        List<UserVo> userVoList1 = new ArrayList<>();
        userVoList1.add(userVo1);
        userListVo.setNode1(userVoList1);

        List<UserVo> userVoList2 = new ArrayList<>();
        userVoList2.add(userVo2);
        userListVo.setNode2(userVoList2);

        return userListVo;
    }

    @PostMapping("/list")
    public UserSortVO listUser(@RequestBody UserSortVO userSortVO){
       return userSortVO;
    }


}
