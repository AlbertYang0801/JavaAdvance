package com.albert.boot.swagger.controller;

import com.albert.boot.swagger.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;

/**
 * @author yangjunwei
 */
@RestController
@Api(tags = "测试模块")
public class SwaggerController {

    @GetMapping("/a")
    @ApiOperation("接口操作")
    public String a() {
        return "a";
    }

    @GetMapping("/query")
    @ApiOperation("新增用户")
    public User getUser(@RequestParam("name") String name) {
        User user = new User();
        user.setName(name);
        user.setAge("1");
        return user;
    }

    @PostMapping("/add")
    @ApiOperation("新增用户")
    public User addUser(@RequestBody User user) {
        user.setId(1);
        return user;
    }

}
