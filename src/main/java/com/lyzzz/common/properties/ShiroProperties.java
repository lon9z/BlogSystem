package com.lyzzz.common.properties;

import lombok.Data;

/**
 * @location： BlogSystem\com.lyzzz.common.properties
 * @creatTime: 2020/7/17  21:00
 * @author: Administrator
 * @remark:
 *
 * shiro 配置参数
 *
 */
@Data
public class ShiroProperties {

    private long sessionTimeout;
    private int cookieTimeout;
    private String anonUrl;
    private String loginUrl;
    private String successUrl;
    private String logoutUrl;
    private String cipherKey; // 盐

}
