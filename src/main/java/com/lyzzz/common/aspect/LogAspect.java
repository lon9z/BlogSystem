package com.lyzzz.common.aspect;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.lyzzz.blog.entity.Log;
import com.lyzzz.blog.entity.User;
import com.lyzzz.blog.service.LogService;
import com.lyzzz.common.exception.GlobalException;
import com.lyzzz.common.utils.HttpContextUtil;
import com.lyzzz.common.utils.IPUtil;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @location： BlogSystem\com.lyzzz.common.aspect
 * @creatTime: 2020/7/18  9:38
 * @author: Administrator
 * @remark:
 *
 * Log 切面
 *
 */
@Aspect
@Component
public class LogAspect {

    @Autowired
    private LogService logService;

    // 切点 切有log注解的方法
    @Pointcut("@annotation(com.lyzzz.common.annotation.Log)")
    public void ponitcut(){

    }

    // 环绕通知
    @Around("ponitcut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws JsonProcessingException {
        Object result = null;

        try {
            result = proceedingJoinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            throw new GlobalException(throwable.getMessage());
        }
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        if (user != null){
            long beginTime = System.currentTimeMillis();
            // 获取Request请求
            HttpServletRequest request = HttpContextUtil.getHttpServletRequest();
            // 设置IP地址
            String ip = IPUtil.getIpAddr(request);
            // 记录时间
            long time = System.currentTimeMillis() - beginTime;
            // 保存日志
            Log log = new Log();
            log.setIp(ip);
            log.setTime(time);
            log.setUsername(user.getUsername());
            logService.saveLog(proceedingJoinPoint,log);

        }

        return result;

    }

}
