package com.lyzzz.common.properties;

import lombok.Data;

/**
 * @location： BlogSystem\com.lyzzz.common.properties
 * @creatTime: 2020/7/17  21:01
 * @author: Administrator
 * @remark:
 *
 * Swagger 配置参数
 *
 */
@Data
public class SwaggerProperties {

    private String basePackage;
    private String title;
    private String description;
    private String author;
    private String url;
    private String email;
    private String name;
    private String version;

}
