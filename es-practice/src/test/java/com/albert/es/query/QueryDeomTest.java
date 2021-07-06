package com.albert.es.query;

import com.albert.es.TestApplication;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.avg.Avg;
import org.elasticsearch.search.aggregations.metrics.max.Max;
import org.elasticsearch.search.sort.SortOrder;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author yangjunwei
 * @date 2021/6/15 下午5:35
 */
@SpringBootTest(classes = TestApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
@Slf4j
public class QueryDeomTest {

    //max 求最大值
    @Test
    public void testMaxValue() throws UnknownHostException {
        //1、指定es集群  cluster.name 是固定的key值，my-application是ES集群的名称
        Settings settings = Settings.builder().put("cluster.name", "Bpm").build();
        //2.创建访问ES服务器的客户端
        TransportClient client = new PreBuiltTransportClient(settings)
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("10.1.10.178"), 9300));

        //aggMax为最大值的别名 ，age是要求最大值的列
        AggregationBuilder builder = AggregationBuilders.max("maxMonitorId").field("monitor-item-id");
        SearchResponse response = client.prepareSearch("bpm1.0-shiya_test-monitoritem-1").addAggregation(builder).get();
        Max max = response.getAggregations().get("maxMonitorId");
        //打印最大值
        System.out.println(max.getValue());
    }

    //avg 求平均值
    @Test
    public void testAvgValue() throws UnknownHostException {
        //1、指定es集群  cluster.name 是固定的key值，my-application是ES集群的名称
        Settings settings = Settings.builder().put("cluster.name", "Bpm").build();
        //2.创建访问ES服务器的客户端
        TransportClient client = new PreBuiltTransportClient(settings)
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("10.1.10.178"), 9300));

        //aggMax为最大值的别名 ，age是要求最大值的列
        AggregationBuilder builder = AggregationBuilders.avg("avgMonitorId").field("monitor-item-id");
        SearchResponse response = client.prepareSearch("bpm1.0-shiya_test-monitoritem-1").addAggregation(builder).get();
        Avg avg = response.getAggregations().get("avgMonitorId");
        //打印最大值
        System.out.println(avg.getValue());
    }

//    @Test
//    public void testSortTop10(){
//        TermsAggregationBuilder termsAggregationBuilder = AggregationBuilders
//                .terms("timestamp").field("timestamp")
//                .subAggregation(AggregationBuilders.max("cpuUsage").field("sysinfo.cpuUsage"))
//                .subAggregation(
//                        AggregationBuilders.topHits("interval")
//                                .sort("timestamp", SortOrder.DESC)
//                        .fetchSource(new String[]{"requestBytes","requestSent","totalTime","response_headers"},null)
//                ).order(BucketOrder)
//
//
//
//
//    }


}


