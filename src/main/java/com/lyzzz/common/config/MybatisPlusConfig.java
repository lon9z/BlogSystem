package com.lyzzz.common.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @location： BlogSystem\com.lyzzz.common.config
 * @creatTime: 2020/7/18  12:20
 * @author: Administrator
 * @remark:
 */
@Configuration
public class MybatisPlusConfig {
    /**
     * MybatisPlus 分页插件
     * */
    @Bean
    public PaginationInterceptor paginationInterceptor(){
        return new PaginationInterceptor();
    }

}
