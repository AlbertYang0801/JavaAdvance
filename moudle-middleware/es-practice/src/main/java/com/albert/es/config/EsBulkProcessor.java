//package com.albert.es.config;
//
//import lombok.extern.slf4j.Slf4j;
//import org.apache.http.client.utils.DateUtils;
//import org.elasticsearch.action.bulk.BackoffPolicy;
//import org.elasticsearch.action.bulk.BulkProcessor;
//import org.elasticsearch.action.bulk.BulkRequest;
//import org.elasticsearch.action.bulk.BulkResponse;
//import org.elasticsearch.client.RestHighLevelClient;
//import org.elasticsearch.common.unit.ByteSizeUnit;
//import org.elasticsearch.common.unit.ByteSizeValue;
//import org.elasticsearch.core.TimeValue;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.Date;
//import java.util.Objects;
//
///**
// * @author yangjunwei
// * @date 2022/6/7 7:29 下午
// */
//@Configuration
//@Slf4j
//public class EsBulkProcessor {
//
//    @Autowired
//    RestHighLevelClient restHighLevelClient;
//
//    private BulkProcessor bulkProcessor = null;
//
//    public BulkProcessor getBulkProcessor() {
//        if (Objects.isNull(bulkProcessor)) {
//            buildBulkProcessorClient();
//        }
//        return bulkProcessor;
//    }
//
//    private void buildBulkProcessorClient() {
//        System.out.println("111");
//        //6.xES可以使用restclient
//        bulkProcessor =  BulkProcessor.builder(restHighLevelClient::bulkAsync, new BulkProcessor.Listener() {
//            private String start;
//            private int number = 0;
//            private int context = 0;
//
//            @Override
//            public void beforeBulk(final long executionId, final BulkRequest request) {
//                start = DateUtils.formatDate(new Date());
//                context = request.requests().size();
//                number = request.numberOfActions();
//                log.info("beforeBulk=====> 序号：{},开始执行 {} 条数据", executionId, request.numberOfActions());
//            }
//
//            @Override
//            public void afterBulk(final long executionId, final BulkRequest request, final BulkResponse response) {
//                if (response.hasFailures()) {
//                    log.error("{} afterBulk error", executionId);
//                } else {
//                    log.info("序号：{}，执行 {} 条数据批量操作成功，共耗费 {} 毫秒。",
//                        executionId, request.numberOfActions(), response.getTook().getMillis());
//                }
//            }
//
//            @Override
//            public void afterBulk(final long executionId, final BulkRequest request, final Throwable failure) {
//                log.error("bulk failed with error:", failure);
//            }
//        })  //每添加5000个request，执行一次bulk操作
//            .setBulkActions(500)
//            //每达到3M的请求size时，执行一次bulk操作
//            .setBulkSize(new ByteSizeValue(3, ByteSizeUnit.MB))
//            //每3s执行一次bulk操作
//            .setFlushInterval(TimeValue.timeValueSeconds(3))
//            //设置并发请求数。默认是1，表示允许执行1个并发请求，积累bulk requests和发送bulk是异步的，其数值表示发送bulk的并发线程数；若设置为0表示二者同步。
//            .setConcurrentRequests(32)
//            //最大重试次数为3次，启动延迟为100ms。
//            .setBackoffPolicy(BackoffPolicy.exponentialBackoff(TimeValue.timeValueMillis(100), 3))
//            .build();
//    }
//
//
//}
