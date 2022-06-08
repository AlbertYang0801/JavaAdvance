package com.albert.es.doc;

import cn.hutool.core.date.DateUtil;
import com.albert.es.TestApplication;
import com.albert.es.config.EsBulkProcessor;
import com.alibaba.fastjson.JSON;
import java.util.HashMap;
import java.util.Map;
import lombok.SneakyThrows;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RestHighLevelClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author yangjunwei
 * @date 2022/6/7 8:36 下午
 */
@SpringBootTest(classes = TestApplication.class)
@RunWith(SpringRunner.class)
public class TestBulkDoc {

    @Autowired
    EsBulkProcessor esBulkProcessor;

    @Autowired
    RestHighLevelClient restHighLevelClient;

    @SneakyThrows
    @Test
    public void testBulk() throws InterruptedException {
        System.out.println("start!!" + DateUtil.now());
        BulkRequest bulkRequest = new BulkRequest();
        for (int i = 0; i < 1000; i++) {
            //创建文档数据
            Map<String, Object> map = new HashMap<>();
            map.put("name", "基础");
            map.put("time", "2022-03-15 16:04:24");
            map.put("price", i);
            //索引请求对象
            IndexRequest indexRequest = new IndexRequest("test-0602", "_doc");
            //指定索引文档内容
            indexRequest.source(map);
            bulkRequest.add(indexRequest);
        }
        restHighLevelClient.bulk(bulkRequest);
        System.out.println("end!!" + DateUtil.now());
        Thread.sleep(50000);
    }

    @SneakyThrows
    @Test
    public void testBulkAsync() {
        System.out.println("start!!" + DateUtil.now());
        BulkRequest bulkRequest = new BulkRequest();
        for (int i = 0; i < 1000; i++) {
            //创建文档数据
            Map<String, Object> map = new HashMap<>();
            map.put("name", "基础");
            map.put("time", "2022-03-15 16:04:24");
            map.put("price", i);
            //索引请求对象
            IndexRequest indexRequest = new IndexRequest("test-0602", "_doc");
            //指定索引文档内容
            indexRequest.source(map);
            bulkRequest.add(indexRequest);
        }
        restHighLevelClient.bulkAsync(bulkRequest, new ActionListener<BulkResponse>() {
            @Override
            public void onResponse(BulkResponse bulkItemResponses) {
                System.out.println(JSON.toJSONString(bulkItemResponses));
                System.out.println("响应时间" + bulkItemResponses.getTook().getSeconds());
            }

            @Override
            public void onFailure(Exception e) {
                System.out.println("执行失败");
            }
        });
        System.out.println("end!!" + DateUtil.now());
        Thread.sleep(50000);
    }

    @SneakyThrows
    @Test
    public void testBulkProcessor() {
        System.out.println("start!!" + DateUtil.now());
        BulkRequest bulkRequest = new BulkRequest();
        for (int i = 0; i < 1000; i++) {
            //创建文档数据
            Map<String, Object> map = new HashMap<>();
            map.put("name", "基础");
            map.put("time", "2022-03-15 16:04:24");
            map.put("price", i);
            //索引请求对象
            IndexRequest indexRequest = new IndexRequest("test-0602", "_doc");
            //指定索引文档内容
            indexRequest.source(map);
            esBulkProcessor.getBulkProcessor().add(indexRequest);
        }
        System.out.println("end!!" + DateUtil.now());
        Thread.sleep(50000);
    }


}