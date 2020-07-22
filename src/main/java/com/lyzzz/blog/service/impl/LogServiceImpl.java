package com.lyzzz.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lyzzz.blog.dao.LogMapper;
import com.lyzzz.blog.entity.Log;
import com.lyzzz.blog.service.LogService;
import com.lyzzz.common.utils.AddressUtil;
import com.lyzzz.common.utils.QueryPage;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @location： BlogSystem\com.lyzzz.blog.service.impl
 * @creatTime: 2020/7/18  10:44
 * @author: Administrator
 * @remark:
 */
@Service
public class LogServiceImpl extends ServiceImpl<LogMapper, Log> implements LogService {

    @Autowired
    private LogMapper logMapper;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void saveLog(ProceedingJoinPoint proceedingJoinPoint, Log log) throws JsonProcessingException {
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        Method method = signature.getMethod();
        com.lyzzz.common.annotation.Log annotation = method.getAnnotation(com.lyzzz.common.annotation.Log.class);
        if (annotation != null) {
            // 注解上的描述
            log.setOperation(annotation.value());
        }
        // 请求的类名
        String className = proceedingJoinPoint.getTarget().getClass().getName();
        // 请求方法名
        String methodName = signature.getName();
        log.setMethod(className + "." + methodName + "()");
        // 请求的方法参数
        Object[] args = proceedingJoinPoint.getArgs();
        // 请求的方法参数名称
        LocalVariableTableParameterNameDiscoverer d = new LocalVariableTableParameterNameDiscoverer();
        String[] parameterNames = d.getParameterNames(method);

        if (args != null && parameterNames != null) {
            StringBuilder params = new StringBuilder();
            params = handleParams(params, args, Arrays.asList(parameterNames));
            String str = params.toString();
            if (str.length() > 100){
                str = str.substring(0, 80) + "...";
                log.setParams(str);
            }
        }
        log.setCreateTime(new Date());
        log.setLocation(AddressUtil.getAddress(log.getIp()));
        this.save(log);

    }

    private StringBuilder handleParams(StringBuilder params, Object[] args, List<String> paramNames) throws JsonProcessingException {
        for (int i = 0; i < args.length; i++) {
            if (args[i] instanceof Map) {
                Set set = ((Map) args[i]).keySet();
                List list = new ArrayList();
                List paramList = new ArrayList();
                for (Object key : set) {
                    list.add(((Map) args[i]).get(key));
                    paramList.add(key);
                }
                // 递归
                return handleParams(params, list.toArray(), paramList);
            } else {
                if (args[i] instanceof Serializable) {
                    Class<?> clazz = args[i].getClass();
                    try {
                        clazz.getDeclaredMethod("toString", new Class[]{null});
                        // 如果不抛出NoSuchMethodException异常，则存在toString()方法
                        params.append(" ").append(paramNames.get(i)).append(objectMapper.writeValueAsString(args[i]));
                    } catch (NoSuchMethodException e) {
                        params.append(" ").append(paramNames.get(i)).append(objectMapper.writeValueAsString(args[i].toString()));
                    }
                }else if (args[i] instanceof MultipartFile){
                    MultipartFile file = (MultipartFile) args[i];
                    params.append(" ").append(paramNames.get(i)).append(": ").append(file.getName());
                }else {
                    params.append(" ").append(paramNames.get(i)).append(": ").append(args[i]);
                }
            }
        }
        return params;

    }

    @Override
    public IPage<Log> list(Log log, QueryPage queryPage) {
        IPage<Log> page = new Page<>(queryPage.getPage(), queryPage.getLimit());
        LambdaQueryWrapper<Log> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(
                StringUtils.isNotBlank(log.getUsername()),
                Log::getUsername,
                log.getUsername()
        );
        queryWrapper.like(
                StringUtils.isNotBlank(log.getIp()),
                Log::getIp,
                log.getIp()
        );
        queryWrapper.orderByDesc(Log::getId);

        return logMapper.selectPage(page, queryWrapper);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        logMapper.deleteById(id);
    }
}
