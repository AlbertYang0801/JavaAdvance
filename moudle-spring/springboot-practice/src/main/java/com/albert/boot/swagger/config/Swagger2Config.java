package com.albert.boot.swagger.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author yangjunwei
 * @date 2024/12/5 13:45
 */
//@EnableSwagger2
//@Configuration
public class Swagger2Config {

    @Value("${swagger.enable}")
    private boolean swaggerEnable;

    @Bean
    public Docket docket(Environment environment){
        return new Docket(DocumentationType.SWAGGER_2)
                //生产环境配置环境变量隐藏
                .enable(swaggerEnable)
                .apiInfo(apiInfo());
    }

    /**
     * 创建该API的基本信息（这些基本信息会展现在文档页面中）
     * 访问地址：http://项目实际地址/swagger-ui.html
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("server")
                .description("API")
                .version("1.0.0")
                .build();
    }

}
