package com.albert.study.kafka.controller;

import com.albert.study.kafka.producer.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Albert
 * @date 2020/7/27 20:33
 */
@RestController
public class KafkaController {

    @Autowired
    private KafkaProducer kafkaProducer;

    @PostMapping("/kafka/sendmsg")
    public void sendMsgToKafka() {
        String str= "{\"_cw_hostname\":\"docpbd09\",\"_cw_host_ip\":\"10.21.33.109\",\"_cw_collect_id\":\"GuoShou\",\"_cw_collect_type\":\"CDC\",\"_cw_message\":\"{\\\"type\\\":\\\"AgentStatBo\\\",\\\"data\\\":{\\\"agentId\\\":\\\"n3s-customers-bg-4\\\",\\\"startTimestamp\\\":1591385691884,\\\"jvmGcBos\\\":[{\\\"agentId\\\":\\\"n3s-customers-bg-4\\\",\\\"startTimestamp\\\":1591385691884,\\\"timestamp\\\":1591661611305,\\\"gcType\\\":\\\"G1\\\",\\\"heapUsed\\\":1431532480,\\\"heapMax\\\":4294967296,\\\"nonHeapUsed\\\":228377520,\\\"nonHeapMax\\\":-1,\\\"gcOldCount\\\":28,\\\"gcOldTime\\\":16507},{\\\"agentId\\\":\\\"n3s-customers-bg-4\\\",\\\"startTimestamp\\\":1591385691884,\\\"timestamp\\\":1591661616305,\\\"gcType\\\":\\\"G1\\\",\\\"heapUsed\\\":1645441984,\\\"heapMax\\\":4294967296,\\\"nonHeapUsed\\\":228377520,\\\"nonHeapMax\\\":-1,\\\"gcOldCount\\\":28,\\\"gcOldTime\\\":16507},{\\\"agentId\\\":\\\"n3s-customers-bg-4\\\",\\\"startTimestamp\\\":1591385691884,\\\"timestamp\\\":1591661621305,\\\"gcType\\\":\\\"G1\\\",\\\"heapUsed\\\":1850962880,\\\"heapMax\\\":4294967296,\\\"nonHeapUsed\\\":228377520,\\\"nonHeapMax\\\":-1,\\\"gcOldCount\\\":28,\\\"gcOldTime\\\":16507},{\\\"agentId\\\":\\\"n3s-customers-bg-4\\\",\\\"startTimestamp\\\":1591385691884,\\\"timestamp\\\":1591661626305,\\\"gcType\\\":\\\"G1\\\",\\\"heapUsed\\\":166505952,\\\"heapMax\\\":4294967296,\\\"nonHeapUsed\\\":228377520,\\\"nonHeapMax\\\":-1,\\\"gcOldCount\\\":28,\\\"gcOldTime\\\":16507},{\\\"agentId\\\":\\\"n3s-customers-bg-4\\\",\\\"startTimestamp\\\":1591385691884,\\\"timestamp\\\":1591661631305,\\\"gcType\\\":\\\"G1\\\",\\\"heapUsed\\\":445427168,\\\"heapMax\\\":4294967296,\\\"nonHeapUsed\\\":228377520,\\\"nonHeapMax\\\":-1,\\\"gcOldCount\\\":28,\\\"gcOldTime\\\":16507},{\\\"agentId\\\":\\\"n3s-customers-bg-4\\\",\\\"startTimestamp\\\":1591385691884,\\\"timestamp\\\":1591661636305,\\\"gcType\\\":\\\"G1\\\",\\\"heapUsed\\\":640462304,\\\"heapMax\\\":4294967296,\\\"nonHeapUsed\\\":228377520,\\\"nonHeapMax\\\":-1,\\\"gcOldCount\\\":28,\\\"gcOldTime\\\":16507}],\\\"jvmGcDetailedBos\\\":[{\\\"agentId\\\":\\\"n3s-customers-bg-4\\\",\\\"startTimestamp\\\":1591385691884,\\\"timestamp\\\":1591661611305,\\\"gcNewCount\\\":28,\\\"gcNewTime\\\":16507,\\\"codeCacheUsed\\\":0.3860247294108073,\\\"newGenUsed\\\":0.6415094339622641,\\\"oldGenUsed\\\":0.02764056622982025,\\\"survivorSpaceUsed\\\":1.0,\\\"permGenUsed\\\":-1.0,\\\"metaspaceUsed\\\":0.9508245132143804},{\\\"agentId\\\":\\\"n3s-customers-bg-4\\\",\\\"startTimestamp\\\":1591385691884,\\\"timestamp\\\":1591661616305,\\\"gcNewCount\\\":28,\\\"gcNewTime\\\":16507,\\\"codeCacheUsed\\\":0.3860247294108073,\\\"newGenUsed\\\":0.7484276729559748,\\\"oldGenUsed\\\":0.02764056622982025,\\\"survivorSpaceUsed\\\":1.0,\\\"permGenUsed\\\":-1.0,\\\"metaspaceUsed\\\":0.9508245132143804},{\\\"agentId\\\":\\\"n3s-customers-bg-4\\\",\\\"startTimestamp\\\":1591385691884,\\\"timestamp\\\":1591661621305,\\\"gcNewCount\\\":28,\\\"gcNewTime\\\":16507,\\\"codeCacheUsed\\\":0.3860247294108073,\\\"newGenUsed\\\":0.8511530398322851,\\\"oldGenUsed\\\":0.02764056622982025,\\\"survivorSpaceUsed\\\":1.0,\\\"permGenUsed\\\":-1.0,\\\"metaspaceUsed\\\":0.9508245132143804},{\\\"agentId\\\":\\\"n3s-customers-bg-4\\\",\\\"startTimestamp\\\":1591385691884,\\\"timestamp\\\":1591661626305,\\\"gcNewCount\\\":28,\\\"gcNewTime\\\":16507,\\\"codeCacheUsed\\\":0.3860247294108073,\\\"newGenUsed\\\":0.012539184952978056,\\\"oldGenUsed\\\":0.02753721922636032,\\\"survivorSpaceUsed\\\":1.0,\\\"permGenUsed\\\":-1.0,\\\"metaspaceUsed\\\":0.9508245132143804},{\\\"agentId\\\":\\\"n3s-customers-bg-4\\\",\\\"startTimestamp\\\":1591385691884,\\\"timestamp\\\":1591661631305,\\\"gcNewCount\\\":28,\\\"gcNewTime\\\":16507,\\\"codeCacheUsed\\\":0.3860247294108073,\\\"newGenUsed\\\":0.15151515151515152,\\\"oldGenUsed\\\":0.02753721922636032,\\\"survivorSpaceUsed\\\":1.0,\\\"permGenUsed\\\":-1.0,\\\"metaspaceUsed\\\":0.9508245132143804},{\\\"agentId\\\":\\\"n3s-customers-bg-4\\\",\\\"startTimestamp\\\":1591385691884,\\\"timestamp\\\":1591661636305,\\\"gcNewCount\\\":28,\\\"gcNewTime\\\":16507,\\\"codeCacheUsed\\\":0.3860247294108073,\\\"newGenUsed\\\":0.24869383490073146,\\\"oldGenUsed\\\":0.02753721922636032,\\\"survivorSpaceUsed\\\":1.0,\\\"permGenUsed\\\":-1.0,\\\"metaspaceUsed\\\":0.9508245132143804}],\\\"cpuLoadBos\\\":[{\\\"agentId\\\":\\\"n3s-customers-bg-4\\\",\\\"startTimestamp\\\":1591385691884,\\\"timestamp\\\":1591661611305,\\\"jvmCpuLoad\\\":0.0027253457151509035,\\\"systemCpuLoad\\\":0.13017915720413828},{\\\"agentId\\\":\\\"n3s-customers-bg-4\\\",\\\"startTimestamp\\\":1591385691884,\\\"timestamp\\\":1591661616305,\\\"jvmCpuLoad\\\":0.002241305497494271,\\\"systemCpuLoad\\\":0.10360893545218727},{\\\"agentId\\\":\\\"n3s-customers-bg-4\\\",\\\"startTimestamp\\\":1591385691884,\\\"timestamp\\\":1591661621305,\\\"jvmCpuLoad\\\":0.0022423784328546234,\\\"systemCpuLoad\\\":0.10982338565417853},{\\\"agentId\\\":\\\"n3s-customers-bg-4\\\",\\\"startTimestamp\\\":1591385691884,\\\"timestamp\\\":1591661626305,\\\"jvmCpuLoad\\\":0.00903531069891526,\\\"systemCpuLoad\\\":0.13039538922306396},{\\\"agentId\\\":\\\"n3s-customers-bg-4\\\",\\\"startTimestamp\\\":1591385691884,\\\"timestamp\\\":1591661631305,\\\"jvmCpuLoad\\\":0.0024214296524239522,\\\"systemCpuLoad\\\":0.10944633623407743},{\\\"agentId\\\":\\\"n3s-customers-bg-4\\\",\\\"startTimestamp\\\":1591385691884,\\\"timestamp\\\":1591661636305,\\\"jvmCpuLoad\\\":0.0023964482114928612,\\\"systemCpuLoad\\\":0.12228836646150741}],\\\"transactionBos\\\":[{\\\"agentId\\\":\\\"n3s-customers-bg-4\\\",\\\"startTimestamp\\\":1591385691884,\\\"timestamp\\\":1591661611305,\\\"collectInterval\\\":5000,\\\"sampledNewCount\\\":0,\\\"sampledContinuationCount\\\":170,\\\"unsampledNewCount\\\":0,\\\"unsampledContinuationCount\\\":0,\\\"skippedNewSkipCount\\\":0,\\\"skippedContinuationCount\\\":0},{\\\"agentId\\\":\\\"n3s-customers-bg-4\\\",\\\"startTimestamp\\\":1591385691884,\\\"timestamp\\\":1591661616305,\\\"collectInterval\\\":5000,\\\"sampledNewCount\\\":0,\\\"sampledContinuationCount\\\":155,\\\"unsampledNewCount\\\":0,\\\"unsampledContinuationCount\\\":0,\\\"skippedNewSkipCount\\\":0,\\\"skippedContinuationCount\\\":0},{\\\"agentId\\\":\\\"n3s-customers-bg-4\\\",\\\"startTimestamp\\\":1591385691884,\\\"timestamp\\\":1591661621305,\\\"collectInterval\\\":5000,\\\"sampledNewCount\\\":0,\\\"sampledContinuationCount\\\":153,\\\"unsampledNewCount\\\":0,\\\"unsampledContinuationCount\\\":0,\\\"skippedNewSkipCount\\\":0,\\\"skippedContinuationCount\\\":0},{\\\"agentId\\\":\\\"n3s-customers-bg-4\\\",\\\"startTimestamp\\\":1591385691884,\\\"timestamp\\\":1591661626305,\\\"collectInterval\\\":5000,\\\"sampledNewCount\\\":0,\\\"sampledContinuationCount\\\":180,\\\"unsampledNewCount\\\":0,\\\"unsampledContinuationCount\\\":0,\\\"skippedNewSkipCount\\\":0,\\\"skippedContinuationCount\\\":0},{\\\"agentId\\\":\\\"n3s-customers-bg-4\\\",\\\"startTimestamp\\\":1591385691884,\\\"timestamp\\\":1591661631305,\\\"collectInterval\\\":5000,\\\"sampledNewCount\\\":0,\\\"sampledContinuationCount\\\":164,\\\"unsampledNewCount\\\":0,\\\"unsampledContinuationCount\\\":0,\\\"skippedNewSkipCount\\\":0,\\\"skippedContinuationCount\\\":0},{\\\"agentId\\\":\\\"n3s-customers-bg-4\\\",\\\"startTimestamp\\\":1591385691884,\\\"timestamp\\\":1591661636305,\\\"collectInterval\\\":5000,\\\"sampledNewCount\\\":0,\\\"sampledContinuationCount\\\":167,\\\"unsampledNewCount\\\":0,\\\"unsampledContinuationCount\\\":0,\\\"skippedNewSkipCount\\\":0,\\\"skippedContinuationCount\\\":0}],\\\"activeTraceBos\\\":[{\\\"agentId\\\":\\\"n3s-customers-bg-4\\\",\\\"startTimestamp\\\":1591385691884,\\\"timestamp\\\":1591661611305,\\\"version\\\":0,\\\"histogramSchemaType\\\":2,\\\"activeTraceHistogram\\\":{\\\"fastCount\\\":4,\\\"normalCount\\\":0,\\\"slowCount\\\":0,\\\"verySlowCount\\\":0}},{\\\"agentId\\\":\\\"n3s-customers-bg-4\\\",\\\"startTimestamp\\\":1591385691884,\\\"timestamp\\\":1591661616305,\\\"version\\\":0,\\\"histogramSchemaType\\\":2,\\\"activeTraceHistogram\\\":{\\\"fastCount\\\":2,\\\"normalCount\\\":0,\\\"slowCount\\\":0,\\\"verySlowCount\\\":0}},{\\\"agentId\\\":\\\"n3s-customers-bg-4\\\",\\\"startTimestamp\\\":1591385691884,\\\"timestamp\\\":1591661621305,\\\"version\\\":0,\\\"histogramSchemaType\\\":2,\\\"activeTraceHistogram\\\":{\\\"fastCount\\\":0,\\\"normalCount\\\":0,\\\"slowCount\\\":0,\\\"verySlowCount\\\":0}},{\\\"agentId\\\":\\\"n3s-customers-bg-4\\\",\\\"startTimestamp\\\":1591385691884,\\\"timestamp\\\":1591661626305,\\\"version\\\":0,\\\"histogramSchemaType\\\":2,\\\"activeTraceHistogram\\\":{\\\"fastCount\\\":0,\\\"normalCount\\\":0,\\\"slowCount\\\":0,\\\"verySlowCount\\\":0}},{\\\"agentId\\\":\\\"n3s-customers-bg-4\\\",\\\"startTimestamp\\\":1591385691884,\\\"timestamp\\\":1591661631305,\\\"version\\\":0,\\\"histogramSchemaType\\\":2,\\\"activeTraceHistogram\\\":{\\\"fastCount\\\":2,\\\"normalCount\\\":0,\\\"slowCount\\\":0,\\\"verySlowCount\\\":0}},{\\\"agentId\\\":\\\"n3s-customers-bg-4\\\",\\\"startTimestamp\\\":1591385691884,\\\"timestamp\\\":1591661636305,\\\"version\\\":0,\\\"histogramSchemaType\\\":2,\\\"activeTraceHistogram\\\":{\\\"fastCount\\\":1,\\\"normalCount\\\":2,\\\"slowCount\\\":0,\\\"verySlowCount\\\":0}}],\\\"dataSourceListBos\\\":[{\\\"dataSourceBoList\\\":[{\\\"agentId\\\":\\\"n3s-customers-bg-4\\\",\\\"startTimestamp\\\":1591385691884,\\\"timestamp\\\":1591661611305,\\\"id\\\":1,\\\"serviceTypeCode\\\":6010,\\\"databaseName\\\":\\\"nehomedb\\\",\\\"jdbcUrl\\\":\\\"jdbc:oracle:thin:@(DESCRIPTION =(ADDRESS = (PROTOCOL = TCP)(HOST =10.20.16.3)(PORT = 10000))(ADDRESS = (PROTOCOL = TCP)(HOST =10.20.16.4)(PORT = 10000))(FAILOVER=yes)(LOAD_BALANCE =yes)(CONNECT_DATA = (SERVER = DEDICATED) (SERVICE_NAME =nehomedb)))\\\",\\\"activeConnectionSize\\\":1,\\\"maxConnectionSize\\\":8},{\\\"agentId\\\":\\\"n3s-customers-bg-4\\\",\\\"startTimestamp\\\":1591385691884,\\\"timestamp\\\":1591661611305,\\\"id\\\":2,\\\"serviceTypeCode\\\":6010,\\\"databaseName\\\":\\\"kehomeb\\\",\\\"jdbcUrl\\\":\\\"jdbc:oracle:thin:@(DESCRIPTION =(ADDRESS = (PROTOCOL = TCP)(HOST = 10.20.16.208)(PORT = 10002))(ADDRESS = (PROTOCOL = TCP)(HOST = 10.20.16.209)(PORT = 10002))(FAILOVER=yes)(LOAD_BALANCE =yes)(CONNECT_DATA = (SERVER = DEDICATED) (SERVICE_NAME = kehomeb)))\\\",\\\"activeConnectionSize\\\":0,\\\"maxConnectionSize\\\":8},{\\\"agentId\\\":\\\"n3s-customers-bg-4\\\",\\\"startTimestamp\\\":1591385691884,\\\"timestamp\\\":1591661611305,\\\"id\\\":3,\\\"serviceTypeCode\\\":6010,\\\"databaseName\\\":\\\"kehomea\\\",\\\"jdbcUrl\\\":\\\"jdbc:oracle:thin:@(DESCRIPTION =(ADDRESS = (PROTOCOL = TCP)(HOST = 10.20.16.203)(PORT = 10001))(ADDRESS = (PROTOCOL = TCP)(HOST = 10.20.16.204)(PORT = 10001))(FAILOVER=yes)(LOAD_BALANCE =yes)(CONNECT_DATA = (SERVER = DEDICATED) (SERVICE_NAME = kehomea)))\\\",\\\"activeConnectionSize\\\":0,\\\"maxConnectionSize\\\":8},{\\\"agentId\\\":\\\"n3s-customers-bg-4\\\",\\\"startTimestamp\\\":1591385691884,\\\"timestamp\\\":1591661611305,\\\"id\\\":4,\\\"serviceTypeCode\\\":6010,\\\"databaseName\\\":\\\"kehomea\\\",\\\"jdbcUrl\\\":\\\"jdbc:oracle:thin:@(DESCRIPTION =(ADDRESS = (PROTOCOL = TCP)(HOST = 10.20.16.203)(PORT = 10001))(ADDRESS = (PROTOCOL = TCP)(HOST = 10.20.16.204)(PORT = 10001))(FAILOVER=yes)(LOAD_BALANCE =yes)(CONNECT_DATA = (SERVER = DEDICATED) (SERVICE_NAME = kehomea)))\\\",\\\"activeConnectionSize\\\":1,\\\"maxConnectionSize\\\":8},{\\\"agentId\\\":\\\"n3s-customers-bg-4\\\",\\\"startTimestamp\\\":1591385691884,\\\"timestamp\\\":1591661611305,\\\"id\\\":5,\\\"serviceTypeCode\\\":6010,\\\"databaseName\\\":\\\"kehomeb\\\",\\\"jdbcUrl\\\":\\\"jdbc:oracle:thin:@(DESCRIPTION =(ADDRESS = (PROTOCOL = TCP)(HOST = 10.20.16.208)(PORT = 10002))(ADDRESS = (PROTOCOL = TCP)(HOST = 10.20.16.209)(PORT = 10002))(FAILOVER=yes)(LOAD_BALANCE =yes)(CONNECT_DATA = (SERVER = DEDICATED) (SERVICE_NAME = kehomeb)))\\\",\\\"activeConnectionSize\\\":1,\\\"maxConnectionSize\\\":8}],\\\"agentId\\\":\\\"n3s-customers-bg-4\\\",\\\"startTimestamp\\\":1591385691884,\\\"timestamp\\\":1591661611305},{\\\"dataSourceBoList\\\":[{\\\"agentId\\\":\\\"n3s-customers-bg-4\\\",\\\"startTimestamp\\\":1591385691884,\\\"timestamp\\\":1591661616305,\\\"id\\\":1,\\\"serviceTypeCode\\\":6010,\\\"databaseName\\\":\\\"nehomedb\\\",\\\"jdbcUrl\\\":\\\"jdbc:oracle:thin:@(DESCRIPTION =(ADDRESS = (PROTOCOL = TCP)(HOST =10.20.16.3)(PORT = 10000))(ADDRESS = (PROTOCOL = TCP)(HOST =10.20.16.4)(PORT = 10000))(FAILOVER=yes)(LOAD_BALANCE =yes)(CONNECT_DATA = (SERVER = DEDICATED) (SERVICE_NAME =nehomedb)))\\\",\\\"activeConnectionSize\\\":1,\\\"maxConnectionSize\\\":8},{\\\"agentId\\\":\\\"n3s-customers-bg-4\\\",\\\"startTimestamp\\\":1591385691884,\\\"timestamp\\\":1591661616305,\\\"id\\\":2,\\\"serviceTypeCode\\\":6010,\\\"databaseName\\\":\\\"kehomeb\\\",\\\"jdbcUrl\\\":\\\"jdbc:oracle:thin:@(DESCRIPTION =(ADDRESS = (PROTOCOL = TCP)(HOST = 10.20.16.208)(PORT = 10002))(ADDRESS = (PROTOCOL = TCP)(HOST = 10.20.16.209)(PORT = 10002))(FAILOVER=yes)(LOAD_BALANCE =yes)(CONNECT_DATA = (SERVER = DEDICATED) (SERVICE_NAME = kehomeb)))\\\",\\\"activeConnectionSize\\\":0,\\\"maxConnectionSize\\\":8},{\\\"agentId\\\":\\\"n3s-customers-bg-4\\\",\\\"startTimestamp\\\":1591385691884,\\\"timestamp\\\":1591661616305,\\\"id\\\":3,\\\"serviceTypeCode\\\":6010,\\\"databaseName\\\":\\\"kehomea\\\",\\\"jdbcUrl\\\":\\\"jdbc:oracle:thin:@(DESCRIPTION =(ADDRESS = (PROTOCOL = TCP)(HOST = 10.20.16.203)(PORT = 10001))(ADDRESS = (PROTOCOL = TCP)(HOST = 10.20.16.204)(PORT = 10001))(FAILOVER=yes)(LOAD_BALANCE =yes)(CONNECT_DATA = (SERVER = DEDICATED) (SERVICE_NAME = kehomea)))\\\",\\\"activeConnectionSize\\\":0,\\\"maxConnectionSize\\\":8},{\\\"agentId\\\":\\\"n3s-customers-bg-4\\\",\\\"startTimestamp\\\":1591385691884,\\\"timestamp\\\":1591661616305,\\\"id\\\":4,\\\"serviceTypeCode\\\":6010,\\\"databaseName\\\":\\\"kehomea\\\",\\\"jdbcUrl\\\":\\\"jdbc:oracle:thin:@(DESCRIPTION =(ADDRESS = (PROTOCOL = TCP)(HOST = 10.20.16.203)(PORT = 10001))(ADDRESS = (PROTOCOL = TCP)(HOST = 10.20.16.204)(PORT = 10001))(FAILOVER=yes)(LOAD_BALANCE =yes)(CONNECT_DATA = (SERVER = DEDICATED) (SERVICE_NAME = kehomea)))\\\",\\\"activeConnectionSize\\\":1,\\\"maxConnectionSize\\\":8},{\\\"agentId\\\":\\\"n3s-customers-bg-4\\\",\\\"startTimestamp\\\":1591385691884,\\\"timestamp\\\":1591661616305,\\\"id\\\":5,\\\"serviceTypeCode\\\":6010,\\\"databaseName\\\":\\\"kehomeb\\\",\\\"jdbcUrl\\\":\\\"jdbc:oracle:thin:@(DESCRIPTION =(ADDRESS = (PROTOCOL = TCP)(HOST = 10.20.16.208)(PORT = 10002))(ADDRESS = (PROTOCOL = TCP)(HOST = 10.20.16.209)(PORT = 10002))(FAILOVER=yes)(LOAD_BALANCE =yes)(CONNECT_DATA = (SERVER = DEDICATED) (SERVICE_NAME = kehomeb)))\\\",\\\"activeConnectionSize\\\":1,\\\"maxConnectionSize\\\":8}],\\\"agentId\\\":\\\"n3s-customers-bg-4\\\",\\\"startTimestamp\\\":1591385691884,\\\"timestamp\\\":1591661616305},{\\\"dataSourceBoList\\\":[{\\\"agentId\\\":\\\"n3s-customers-bg-4\\\",\\\"startTimestamp\\\":1591385691884,\\\"timestamp\\\":1591661621305,\\\"id\\\":1,\\\"serviceTypeCode\\\":6010,\\\"databaseName\\\":\\\"nehomedb\\\",\\\"jdbcUrl\\\":\\\"jdbc:oracle:thin:@(DESCRIPTION =(ADDRESS = (PROTOCOL = TCP)(HOST =10.20.16.3)(PORT = 10000))(ADDRESS = (PROTOCOL = TCP)(HOST =10.20.16.4)(PORT = 10000))(FAILOVER=yes)(LOAD_BALANCE =yes)(CONNECT_DATA = (SERVER = DEDICATED) (SERVICE_NAME =nehomedb)))\\\",\\\"activeConnectionSize\\\":1,\\\"maxConnectionSize\\\":8},{\\\"agentId\\\":\\\"n3s-customers-bg-4\\\",\\\"startTimestamp\\\":1591385691884,\\\"timestamp\\\":1591661621305,\\\"id\\\":2,\\\"serviceTypeCode\\\":6010,\\\"databaseName\\\":\\\"kehomeb\\\",\\\"jdbcUrl\\\":\\\"jdbc:oracle:thin:@(DESCRIPTION =(ADDRESS = (PROTOCOL = TCP)(HOST = 10.20.16.208)(PORT = 10002))(ADDRESS = (PROTOCOL = TCP)(HOST = 10.20.16.209)(PORT = 10002))(FAILOVER=yes)(LOAD_BALANCE =yes)(CONNECT_DATA = (SERVER = DEDICATED) (SERVICE_NAME = kehomeb)))\\\",\\\"activeConnectionSize\\\":0,\\\"maxConnectionSize\\\":8},{\\\"agentId\\\":\\\"n3s-customers-bg-4\\\",\\\"startTimestamp\\\":1591385691884,\\\"timestamp\\\":1591661621305,\\\"id\\\":3,\\\"serviceTypeCode\\\":6010,\\\"databaseName\\\":\\\"kehomea\\\",\\\"jdbcUrl\\\":\\\"jdbc:oracle:thin:@(DESCRIPTION =(ADDRESS = (PROTOCOL = TCP)(HOST = 10.20.16.203)(PORT = 10001))(ADDRESS = (PROTOCOL = TCP)(HOST = 10.20.16.204)(PORT = 10001))(FAILOVER=yes)(LOAD_BALANCE =yes)(CONNECT_DATA = (SERVER = DEDICATED) (SERVICE_NAME = kehomea)))\\\",\\\"activeConnectionSize\\\":0,\\\"maxConnectionSize\\\":8},{\\\"agentId\\\":\\\"n3s-customers-bg-4\\\",\\\"startTimestamp\\\":1591385691884,\\\"timestamp\\\":1591661621305,\\\"id\\\":4,\\\"serviceTypeCode\\\":6010,\\\"databaseName\\\":\\\"kehomea\\\",\\\"jdbcUrl\\\":\\\"jdbc:oracle:thin:@(DESCRIPTION =(ADDRESS = (PROTOCOL = TCP)(HOST = 10.20.16.203)(PORT = 10001))(ADDRESS = (PROTOCOL = TCP)(HOST = 10.20.16.204)(PORT = 10001))(FAILOVER=yes)(LOAD_BALANCE =yes)(CONNECT_DATA = (SERVER = DEDICATED) (SERVICE_NAME = kehomea)))\\\",\\\"activeConnectionSize\\\":1,\\\"maxConnectionSize\\\":8},{\\\"agentId\\\":\\\"n3s-customers-bg-4\\\",\\\"startTimestamp\\\":1591385691884,\\\"timestamp\\\":1591661621305,\\\"id\\\":5,\\\"serviceTypeCode\\\":6010,\\\"databaseName\\\":\\\"kehomeb\\\",\\\"jdbcUrl\\\":\\\"jdbc:oracle:thin:@(DESCRIPTION =(ADDRESS = (PROTOCOL = TCP)(HOST = 10.20.16.208)(PORT = 10002))(ADDRESS = (PROTOCOL = TCP)(HOST = 10.20.16.209)(PORT = 10002))(FAILOVER=yes)(LOAD_BALANCE =yes)(CONNECT_DATA = (SERVER = DEDICATED) (SERVICE_NAME = kehomeb)))\\\",\\\"activeConnectionSize\\\":1,\\\"maxConnectionSize\\\":8}],\\\"agentId\\\":\\\"n3s-customers-bg-4\\\",\\\"startTimestamp\\\":1591385691884,\\\"timestamp\\\":1591661621305},{\\\"dataSourceBoList\\\":[{\\\"agentId\\\":\\\"n3s-customers-bg-4\\\",\\\"startTimestamp\\\":1591385691884,\\\"timestamp\\\":1591661626305,\\\"id\\\":1,\\\"serviceTypeCode\\\":6010,\\\"databaseName\\\":\\\"nehomedb\\\",\\\"jdbcUrl\\\":\\\"jdbc:oracle:thin:@(DESCRIPTION =(ADDRESS = (PROTOCOL = TCP)(HOST =10.20.16.3)(PORT = 10000))(ADDRESS = (PROTOCOL = TCP)(HOST =10.20.16.4)(PORT = 10000))(FAILOVER=yes)(LOAD_BALANCE =yes)(CONNECT_DATA = (SERVER = DEDICATED) (SERVICE_NAME =nehomedb)))\\\",\\\"activeConnectionSize\\\":1,\\\"maxConnectionSize\\\":8},{\\\"agentId\\\":\\\"n3s-customers-bg-4\\\",\\\"startTimestamp\\\":1591385691884,\\\"timestamp\\\":1591661626305,\\\"id\\\":2,\\\"serviceTypeCode\\\":6010,\\\"databaseName\\\":\\\"kehomeb\\\",\\\"jdbcUrl\\\":\\\"jdbc:oracle:thin:@(DESCRIPTION =(ADDRESS = (PROTOCOL = TCP)(HOST = 10.20.16.208)(PORT = 10002))(ADDRESS = (PROTOCOL = TCP)(HOST = 10.20.16.209)(PORT = 10002))(FAILOVER=yes)(LOAD_BALANCE =yes)(CONNECT_DATA = (SERVER = DEDICATED) (SERVICE_NAME = kehomeb)))\\\",\\\"activeConnectionSize\\\":0,\\\"maxConnectionSize\\\":8},{\\\"agentId\\\":\\\"n3s-customers-bg-4\\\",\\\"startTimestamp\\\":1591385691884,\\\"timestamp\\\":1591661626305,\\\"id\\\":3,\\\"serviceTypeCode\\\":6010,\\\"databaseName\\\":\\\"kehomea\\\",\\\"jdbcUrl\\\":\\\"jdbc:oracle:thin:@(DESCRIPTION =(ADDRESS = (PROTOCOL = TCP)(HOST = 10.20.16.203)(PORT = 10001))(ADDRESS = (PROTOCOL = TCP)(HOST = 10.20.16.204)(PORT = 10001))(FAILOVER=yes)(LOAD_BALANCE =yes)(CONNECT_DATA = (SERVER = DEDICATED) (SERVICE_NAME = kehomea)))\\\",\\\"activeConnectionSize\\\":0,\\\"maxConnectionSize\\\":8},{\\\"agentId\\\":\\\"n3s-customers-bg-4\\\",\\\"startTimestamp\\\":1591385691884,\\\"timestamp\\\":1591661626305,\\\"id\\\":4,\\\"serviceTypeCode\\\":6010,\\\"databaseName\\\":\\\"kehomea\\\",\\\"jdbcUrl\\\":\\\"jdbc:oracle:thin:@(DESCRIPTION =(ADDRESS = (PROTOCOL = TCP)(HOST = 10.20.16.203)(PORT = 10001))(ADDRESS = (PROTOCOL = TCP)(HOST = 10.20.16.204)(PORT = 10001))(FAILOVER=yes)(LOAD_BALANCE =yes)(CONNECT_DATA = (SERVER = DEDICATED) (SERVICE_NAME = kehomea)))\\\",\\\"activeConnectionSize\\\":1,\\\"maxConnectionSize\\\":8},{\\\"agentId\\\":\\\"n3s-customers-bg-4\\\",\\\"startTimestamp\\\":1591385691884,\\\"timestamp\\\":1591661626305,\\\"id\\\":5,\\\"serviceTypeCode\\\":6010,\\\"databaseName\\\":\\\"kehomeb\\\",\\\"jdbcUrl\\\":\\\"jdbc:oracle:thin:@(DESCRIPTION =(ADDRESS = (PROTOCOL = TCP)(HOST = 10.20.16.208)(PORT = 10002))(ADDRESS = (PROTOCOL = TCP)(HOST = 10.20.16.209)(PORT = 10002))(FAILOVER=yes)(LOAD_BALANCE =yes)(CONNECT_DATA = (SERVER = DEDICATED) (SERVICE_NAME = kehomeb)))\\\",\\\"activeConnectionSize\\\":1,\\\"maxConnectionSize\\\":8}],\\\"agentId\\\":\\\"n3s-customers-bg-4\\\",\\\"startTimestamp\\\":1591385691884,\\\"timestamp\\\":1591661626305},{\\\"dataSourceBoList\\\":[{\\\"agentId\\\":\\\"n3s-customers-bg-4\\\",\\\"startTimestamp\\\":1591385691884,\\\"timestamp\\\":1591661631305,\\\"id\\\":1,\\\"serviceTypeCode\\\":6010,\\\"databaseName\\\":\\\"nehomedb\\\",\\\"jdbcUrl\\\":\\\"jdbc:oracle:thin:@(DESCRIPTION =(ADDRESS = (PROTOCOL = TCP)(HOST =10.20.16.3)(PORT = 10000))(ADDRESS = (PROTOCOL = TCP)(HOST =10.20.16.4)(PORT = 10000))(FAILOVER=yes)(LOAD_BALANCE =yes)(CONNECT_DATA = (SERVER = DEDICATED) (SERVICE_NAME =nehomedb)))\\\",\\\"activeConnectionSize\\\":1,\\\"maxConnectionSize\\\":8},{\\\"agentId\\\":\\\"n3s-customers-bg-4\\\",\\\"startTimestamp\\\":1591385691884,\\\"timestamp\\\":1591661631305,\\\"id\\\":2,\\\"serviceTypeCode\\\":6010,\\\"databaseName\\\":\\\"kehomeb\\\",\\\"jdbcUrl\\\":\\\"jdbc:oracle:thin:@(DESCRIPTION =(ADDRESS = (PROTOCOL = TCP)(HOST = 10.20.16.208)(PORT = 10002))(ADDRESS = (PROTOCOL = TCP)(HOST = 10.20.16.209)(PORT = 10002))(FAILOVER=yes)(LOAD_BALANCE =yes)(CONNECT_DATA = (SERVER = DEDICATED) (SERVICE_NAME = kehomeb)))\\\",\\\"activeConnectionSize\\\":0,\\\"maxConnectionSize\\\":8},{\\\"agentId\\\":\\\"n3s-customers-bg-4\\\",\\\"startTimestamp\\\":1591385691884,\\\"timestamp\\\":1591661631305,\\\"id\\\":3,\\\"serviceTypeCode\\\":6010,\\\"databaseName\\\":\\\"kehomea\\\",\\\"jdbcUrl\\\":\\\"jdbc:oracle:thin:@(DESCRIPTION =(ADDRESS = (PROTOCOL = TCP)(HOST = 10.20.16.203)(PORT = 10001))(ADDRESS = (PROTOCOL = TCP)(HOST = 10.20.16.204)(PORT = 10001))(FAILOVER=yes)(LOAD_BALANCE =yes)(CONNECT_DATA = (SERVER = DEDICATED) (SERVICE_NAME = kehomea)))\\\",\\\"activeConnectionSize\\\":0,\\\"maxConnectionSize\\\":8},{\\\"agentId\\\":\\\"n3s-customers-bg-4\\\",\\\"startTimestamp\\\":1591385691884,\\\"timestamp\\\":1591661631305,\\\"id\\\":4,\\\"serviceTypeCode\\\":6010,\\\"databaseName\\\":\\\"kehomea\\\",\\\"jdbcUrl\\\":\\\"jdbc:oracle:thin:@(DESCRIPTION =(ADDRESS = (PROTOCOL = TCP)(HOST = 10.20.16.203)(PORT = 10001))(ADDRESS = (PROTOCOL = TCP)(HOST = 10.20.16.204)(PORT = 10001))(FAILOVER=yes)(LOAD_BALANCE =yes)(CONNECT_DATA = (SERVER = DEDICATED) (SERVICE_NAME = kehomea)))\\\",\\\"activeConnectionSize\\\":1,\\\"maxConnectionSize\\\":8},{\\\"agentId\\\":\\\"n3s-customers-bg-4\\\",\\\"startTimestamp\\\":1591385691884,\\\"timestamp\\\":1591661631305,\\\"id\\\":5,\\\"serviceTypeCode\\\":6010,\\\"databaseName\\\":\\\"kehomeb\\\",\\\"jdbcUrl\\\":\\\"jdbc:oracle:thin:@(DESCRIPTION =(ADDRESS = (PROTOCOL = TCP)(HOST = 10.20.16.208)(PORT = 10002))(ADDRESS = (PROTOCOL = TCP)(HOST = 10.20.16.209)(PORT = 10002))(FAILOVER=yes)(LOAD_BALANCE =yes)(CONNECT_DATA = (SERVER = DEDICATED) (SERVICE_NAME = kehomeb)))\\\",\\\"activeConnectionSize\\\":1,\\\"maxConnectionSize\\\":8}],\\\"agentId\\\":\\\"n3s-customers-bg-4\\\",\\\"startTimestamp\\\":1591385691884,\\\"timestamp\\\":1591661631305},{\\\"dataSourceBoList\\\":[{\\\"agentId\\\":\\\"n3s-customers-bg-4\\\",\\\"startTimestamp\\\":1591385691884,\\\"timestamp\\\":1591661636305,\\\"id\\\":1,\\\"serviceTypeCode\\\":6010,\\\"databaseName\\\":\\\"nehomedb\\\",\\\"jdbcUrl\\\":\\\"jdbc:oracle:thin:@(DESCRIPTION =(ADDRESS = (PROTOCOL = TCP)(HOST =10.20.16.3)(PORT = 10000))(ADDRESS = (PROTOCOL = TCP)(HOST =10.20.16.4)(PORT = 10000))(FAILOVER=yes)(LOAD_BALANCE =yes)(CONNECT_DATA = (SERVER = DEDICATED) (SERVICE_NAME =nehomedb)))\\\",\\\"activeConnectionSize\\\":1,\\\"maxConnectionSize\\\":8},{\\\"agentId\\\":\\\"n3s-customers-bg-4\\\",\\\"startTimestamp\\\":1591385691884,\\\"timestamp\\\":1591661636305,\\\"id\\\":2,\\\"serviceTypeCode\\\":6010,\\\"databaseName\\\":\\\"kehomeb\\\",\\\"jdbcUrl\\\":\\\"jdbc:oracle:thin:@(DESCRIPTION =(ADDRESS = (PROTOCOL = TCP)(HOST = 10.20.16.208)(PORT = 10002))(ADDRESS = (PROTOCOL = TCP)(HOST = 10.20.16.209)(PORT = 10002))(FAILOVER=yes)(LOAD_BALANCE =yes)(CONNECT_DATA = (SERVER = DEDICATED) (SERVICE_NAME = kehomeb)))\\\",\\\"activeConnectionSize\\\":0,\\\"maxConnectionSize\\\":8},{\\\"agentId\\\":\\\"n3s-customers-bg-4\\\",\\\"startTimestamp\\\":1591385691884,\\\"timestamp\\\":1591661636305,\\\"id\\\":3,\\\"serviceTypeCode\\\":6010,\\\"databaseName\\\":\\\"kehomea\\\",\\\"jdbcUrl\\\":\\\"jdbc:oracle:thin:@(DESCRIPTION =(ADDRESS = (PROTOCOL = TCP)(HOST = 10.20.16.203)(PORT = 10001))(ADDRESS = (PROTOCOL = TCP)(HOST = 10.20.16.204)(PORT = 10001))(FAILOVER=yes)(LOAD_BALANCE =yes)(CONNECT_DATA = (SERVER = DEDICATED) (SERVICE_NAME = kehomea)))\\\",\\\"activeConnectionSize\\\":0,\\\"maxConnectionSize\\\":8},{\\\"agentId\\\":\\\"n3s-customers-bg-4\\\",\\\"startTimestamp\\\":1591385691884,\\\"timestamp\\\":1591661636305,\\\"id\\\":4,\\\"serviceTypeCode\\\":6010,\\\"databaseName\\\":\\\"kehomea\\\",\\\"jdbcUrl\\\":\\\"jdbc:oracle:thin:@(DESCRIPTION =(ADDRESS = (PROTOCOL = TCP)(HOST = 10.20.16.203)(PORT = 10001))(ADDRESS = (PROTOCOL = TCP)(HOST = 10.20.16.204)(PORT = 10001))(FAILOVER=yes)(LOAD_BALANCE =yes)(CONNECT_DATA = (SERVER = DEDICATED) (SERVICE_NAME = kehomea)))\\\",\\\"activeConnectionSize\\\":1,\\\"maxConnectionSize\\\":8},{\\\"agentId\\\":\\\"n3s-customers-bg-4\\\",\\\"startTimestamp\\\":1591385691884,\\\"timestamp\\\":1591661636305,\\\"id\\\":5,\\\"serviceTypeCode\\\":6010,\\\"databaseName\\\":\\\"kehomeb\\\",\\\"jdbcUrl\\\":\\\"jdbc:oracle:thin:@(DESCRIPTION =(ADDRESS = (PROTOCOL = TCP)(HOST = 10.20.16.208)(PORT = 10002))(ADDRESS = (PROTOCOL = TCP)(HOST = 10.20.16.209)(PORT = 10002))(FAILOVER=yes)(LOAD_BALANCE =yes)(CONNECT_DATA = (SERVER = DEDICATED) (SERVICE_NAME = kehomeb)))\\\",\\\"activeConnectionSize\\\":1,\\\"maxConnectionSize\\\":8}],\\\"agentId\\\":\\\"n3s-customers-bg-4\\\",\\\"startTimestamp\\\":1591385691884,\\\"timestamp\\\":1591661636305}],\\\"responseTimeBos\\\":[{\\\"agentId\\\":\\\"n3s-customers-bg-4\\\",\\\"startTimestamp\\\":1591385691884,\\\"timestamp\\\":1591661611305,\\\"avg\\\":81,\\\"max\\\":3128},{\\\"agentId\\\":\\\"n3s-customers-bg-4\\\",\\\"startTimestamp\\\":1591385691884,\\\"timestamp\\\":1591661616305,\\\"avg\\\":65,\\\"max\\\":3714},{\\\"agentId\\\":\\\"n3s-customers-bg-4\\\",\\\"startTimestamp\\\":1591385691884,\\\"timestamp\\\":1591661621305,\\\"avg\\\":27,\\\"max\\\":389},{\\\"agentId\\\":\\\"n3s-customers-bg-4\\\",\\\"startTimestamp\\\":1591385691884,\\\"timestamp\\\":1591661626305,\\\"avg\\\":42,\\\"max\\\":1250},{\\\"agentId\\\":\\\"n3s-customers-bg-4\\\",\\\"startTimestamp\\\":1591385691884,\\\"timestamp\\\":1591661631305,\\\"avg\\\":29,\\\"max\\\":223},{\\\"agentId\\\":\\\"n3s-customers-bg-4\\\",\\\"startTimestamp\\\":1591385691884,\\\"timestamp\\\":1591661636305,\\\"avg\\\":63,\\\"max\\\":2167}],\\\"deadlockThreadCountBos\\\":[],\\\"fileDescriptorBos\\\":[],\\\"directBufferBos\\\":[]},\\\"timestamp\\\":1591661636307}\",\"_cw_log_path\":\"[PCAS_ALLBO],[PCAS_AIOP]\",\"timestamp\":\"1591661636307\",\"_cw_collect_time\":\"1591661636409\"}";
        kafkaProducer.send(str);
    }


}
