package com.albert.mysql.mapper;

import com.albert.mysql.TestApplication;
import com.albert.mysql.defaultsource.mapper.DefaultUserInfoMapper;
import com.albert.mysql.model.UserInfoPO;
import com.albert.mysql.other.mapper.OtherUserInfoMapper;
import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author Albert
 * @date 2020/10/29 21:16
 */
@SpringBootTest(classes = TestApplication.class)
@RunWith(SpringRunner.class)
public class TestMapper {

    @Resource
    DefaultUserInfoMapper defaultUserInfoMapper;

    @Resource
    OtherUserInfoMapper otherUserInfoMapper;

    /**
     * 测试第一个数据源
     */
    @Test
    public void testDefaultDataSource(){
        List<UserInfoPO> userInfoList = defaultUserInfoMapper.getUserInfoList();
        System.out.println(userInfoList);
    }

    /**
     * 测试第二个数据源
     */
    @Test
    public void testUser(){
        List<UserInfoPO> userInfoList = otherUserInfoMapper.getUserInfoList();
        System.out.println(userInfoList);
    }


    @Test
    public void getTablesName(){
        List<Map> list = defaultUserInfoMapper.listTable();
        System.out.println(JSON.toJSONString(list));
        for (Map map : list) {
            String tableName = map.get("TABLE_NAME").toString();
            defaultUserInfoMapper.discareTables(tableName);
        }
    }

    @Test
    public void discardTables(){
        String str="ail_mongodb_metric_data,ali_drds_instances,ali_ecs_instances,ali_ecs_metric_data,ali_ecs_metric_tmp_data,ali_ecs_overview,ali_ecs_spec,ali_global_business_config,ali_global_business_info,ali_global_org_config,ali_list_buckets,ali_list_objects,ali_list_resource_group,ali_machine_info,ali_metering_query,ali_mongodb_instances,ali_mongodb_metric_data,ali_mongodb_spec,ali_mysql_instances,ali_mysql_metric_data,ali_ops,ali_oss_metric_data,ali_overview,ali_p_ninety_five_result,ali_pangu,ali_rds_spec,ali_redis_instances,ali_redis_metric_data,ali_redis_spec,sys_user,user_tenant";
        String[] split = str.split(",");
        for (String table : split) {
            defaultUserInfoMapper.discareTables(table);
        }
    }

    @Test
    public void importTables(){
        String str="ail_mongodb_metric_data,ali_drds_instances,ali_ecs_instances,ali_ecs_metric_data,ali_ecs_metric_tmp_data,ali_ecs_overview,ali_ecs_spec,ali_global_business_config,ali_global_business_info,ali_global_org_config,ali_list_buckets,ali_list_objects,ali_list_resource_group,ali_machine_info,ali_metering_query,ali_mongodb_instances,ali_mongodb_metric_data,ali_mongodb_spec,ali_mysql_instances,ali_mysql_metric_data,ali_ops,ali_oss_metric_data,ali_overview,ali_p_ninety_five_result,ali_pangu,ali_rds_spec,ali_redis_instances,ali_redis_metric_data,ali_redis_spec,user_tenant";
        String[] split = str.split(",");
        for (String table : split) {
            defaultUserInfoMapper.importTables(table);
        }
    }



}
