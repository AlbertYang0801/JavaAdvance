package com.albert.mail.utils;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Albert
 */
@Component
@Data
public class ConfUtil {

    @Value("${mail.userName}")
    private String userName;

    @Value("${mail.password}")
    private String password;

    @Value("${mail.host}")
    private String host;

    @Value("${mail.port}")
    private String port;

    @Value("${mail.protocol}")
    private String protocol;

    @Value("${mail.isSsl}")
    private String isSsl;

    @Value("${mail.debug}")
    private String mailDebug;


}


