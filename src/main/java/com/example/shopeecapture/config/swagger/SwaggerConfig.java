package com.example.shopeecapture.config.swagger;

import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import com.google.common.base.Predicates;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
@EnableSwaggerBootstrapUI
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        List<Parameter> pars = new ArrayList<>();
        return new Docket(DocumentationType.SWAGGER_2)
                //apiInfo指定测试文档基本信息，这部分将在页面展示
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.any())//所有接口暴露给swagger
                .paths(Predicates.not(PathSelectors.regex("/error.*")))
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(pars);
    }

    @Value("${server.port}")
    private String servePrort;

    //基本信息，页面展示
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Swagger Apis Testing Page")
                .description("http://localhost:"+ servePrort +"/swagger-ui.html")
                .contact("OttoOu")
                .build();
    }
}