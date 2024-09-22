package com.albert.custom.config;

import com.albert.message.db.annotations.ConditionalGlobalMessageOnMysql;
import com.albert.message.db.config.MessageMysqlAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 统一管控mysql配置
 *
 * @author yangjunwei
 * @date 2024/9/19
 */
@ConditionalGlobalMessageOnMysql
@Configuration
@Import(MessageMysqlAutoConfiguration.class)
public class CustomMysqlAutoConfiguration {


}
