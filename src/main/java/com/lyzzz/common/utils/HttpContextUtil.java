package com.lyzzz.common.utils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * @location： BlogSystem\com.lyzzz.common.utils
 * @creatTime: 2020/7/18  11:30
 * @author: Administrator
 * @remark:
 *
 * 获取HttpServletRequest 对象
 */
public class HttpContextUtil {
    public static HttpServletRequest getHttpServletRequest(){
        return ((ServletRequestAttributes)
                Objects.requireNonNull(RequestContextHolder.getRequestAttributes()))
                .getRequest();
    }
}
