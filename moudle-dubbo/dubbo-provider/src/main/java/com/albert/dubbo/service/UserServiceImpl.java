package com.albert.dubbo.service;

import com.albert.dubbo.bean.User;
import org.apache.dubbo.config.annotation.DubboService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * @author yangjunwei
 * @date 2024/7/25
 */
@DubboService
@Path("/user")
@Produces
public class UserServiceImpl implements UserService {

    @Override
    @GET
    @Path("/{uid}")
    @Produces(MediaType.APPLICATION_JSON)
    public User getUser(String uid) {
        return new User(uid, "albert");
    }

}
