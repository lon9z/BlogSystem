package com.lyzzz.common.config;

import com.lyzzz.common.properties.BlogProperties;
import com.lyzzz.common.properties.SwaggerProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @location： BlogSystem\com.lyzzz.common.config
 * @creatTime: 2020/7/17  19:39
 * @author: Administrator
 * @remark:
 *
 * Swagger 配置类
 *
 */
// 声明配置类
@Configuration
// 在dev使用Swagger2
@Profile("dev")
// 使用Swagger注解
@EnableSwagger2
public class SwaggerConfig {

    @Autowired
    private BlogProperties blogProperties;

    @Bean
    public Docket createRestApi(){
        SwaggerProperties swagger = blogProperties.getSwagger();
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo(swagger))
                .select()
                .apis(RequestHandlerSelectors.basePackage(swagger.getBasePackage()))
                .build();

    }

    // apiInfo
    private ApiInfo apiInfo(SwaggerProperties swagger) {
        return new ApiInfoBuilder()
                .title(swagger.getTitle())
                .description(swagger.getDescription())
                .termsOfServiceUrl(swagger.getUrl())
                .contact(new Contact(swagger.getAuthor(), swagger.getUrl(), swagger.getEmail()))
                .version(swagger.getVersion())
                .build();
    }

}
