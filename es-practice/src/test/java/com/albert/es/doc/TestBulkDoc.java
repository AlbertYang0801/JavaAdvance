package com.albert.es.doc;

import com.albert.es.TestApplication;
import com.albert.es.config.EsBulkProcessor;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.bulk.BulkProcessor;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.rest.RestStatus;
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

    /**
     * 添加文档的测试
     */
    @Test
    public void testBulk() throws InterruptedException {
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
        Thread.sleep(50000);
    }


}