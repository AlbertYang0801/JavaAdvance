package com.albert.handler;

import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.Param;
import org.jooq.impl.DSL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import top.javatool.canal.client.annotation.CanalTable;
import top.javatool.canal.client.context.CanalContext;
import top.javatool.canal.client.handler.EntryHandler;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.jooq.impl.DSL.field;
import static org.jooq.impl.DSL.table;

/**
 * @author yangjunwei
 * @date 2025/3/14 14:07
 */
@CanalTable(value = "all")
@Component
public class DefaultEntryHandler implements EntryHandler<Map<String, String>> {

    @Resource
    private DSLContext dsl;

    @Override
    public void insert(Map<String, String> map) {
        String table = CanalContext.getModel().getTable();
        List<Field<Object>> fields = map.keySet().stream().map(DSL::field).collect(Collectors.toList());
        List<Param<String>> values = map.values().stream().map(DSL::value).collect(Collectors.toList());
        int execute = dsl.insertInto(table(table)).columns(fields).values(values).execute();
    }

    @Override
    public void update(Map<String, String> before, Map<String, String> after) {
        String table = CanalContext.getModel().getTable();
        Map<Field<Object>, String> map = after.entrySet().stream().filter(entry -> before.get(entry.getKey()) != null)
                .collect(Collectors.toMap(entry -> field(entry.getKey()), Map.Entry::getValue));
        dsl.update(table(table)).set(map).where(field("id").eq(after.get("id"))).execute();
    }

    @Override
    public void delete(Map<String, String> map) {
        String table = CanalContext.getModel().getTable();
        dsl.delete(table(table)).where(field("id").eq(map.get("id"))).execute();
    }

}