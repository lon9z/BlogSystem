package com.lyzzz.common.properties;

import lombok.Data;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

/**
 * @location： BlogSystem\com.lyzzz.common.properties
 * @creatTime: 2020/7/17  20:27
 * @author: Administrator
 * @remark:
 *
 * 系统配置类
 *
 */
// lombok注解
@Data
// 声明配置文件类
@SpringBootConfiguration
// 加载指定的配置文件
@PropertySource(value = {"classpath:blog.properties"})
// 参数注入
@ConfigurationProperties(prefix = "blog")
public class BlogProperties {

    private ShiroProperties shiro = new ShiroProperties();
    private SwaggerProperties swagger = new SwaggerProperties();

}
