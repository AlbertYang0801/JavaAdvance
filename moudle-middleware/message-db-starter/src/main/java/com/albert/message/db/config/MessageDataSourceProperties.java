package com.albert.message.db.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author yangjunwei
 * @date 2024/9/19
 */
@ConfigurationProperties(prefix = "message.datasource")
@Data
public class MessageDataSourceProperties {

    private String driverClassName;

    private String url;

    private String username;

    private String password;


}
