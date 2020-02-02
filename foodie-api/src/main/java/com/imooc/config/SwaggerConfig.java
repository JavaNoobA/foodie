package com.imooc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by eru on 2020/2/2.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    // http://localhost:8080/swagger-ui.html
    // http://localhost:8080/doc.html
    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.imooc.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    public ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("天天吃货 电商平台接口api")
                .contact(new Contact("imooc",
                        "http://www.imooc.com",
                        "zhaopf1996@gmail.com"))
                .description("后台接口api详细信息")
                .version("1.0.1")
                .termsOfServiceUrl("https://www.imooc.com")
                .build();
    }
}
