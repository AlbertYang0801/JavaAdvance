package com.albert.handler;

import com.albert.model.BarGroupPermission;
import org.springframework.stereotype.Component;
import top.javatool.canal.client.annotation.CanalTable;
import top.javatool.canal.client.handler.EntryHandler;

/**
 * @author yangjunwei
 * @date 2025/3/14 14:07
 */
@Component
@CanalTable(value = "bar_group_permission")
public class BarGroupPermissionHandler implements EntryHandler<BarGroupPermission> {

    @Override
    public void insert(BarGroupPermission user) {
        System.out.println("insert message " + user);
    }

    @Override
    public void update(BarGroupPermission before, BarGroupPermission after) {
        System.out.println("update before " + before);
        System.out.println("update message " + after);

    }

    @Override
    public void delete(BarGroupPermission user) {
        System.out.println("delete message " + user);
    }

}