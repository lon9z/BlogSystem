package com.lyzzz.blog.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.lyzzz.blog.entity.Log;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lyzzz.common.utils.QueryPage;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.scheduling.annotation.Async;

/**
 * @location： BlogSystem\com.lyzzz.blog.service
 * @creatTime: 2020/7/18  10:44
 * @author: Administrator
 * @remark:
 */
public interface LogService extends IService<Log> {

    /**基于@Async标注的方法，称之为异步方法
     *  这些方法将在执行的时候，将会在独立的线程中被执行，
     *  调用者无需等待它的完成，即可继续其他的操作。
     */
    @Async
    void saveLog(ProceedingJoinPoint proceedingJoinPoint, Log log) throws JsonProcessingException;

    IPage<Log> list(Log log, QueryPage queryPage);

    void delete(Long id);
}
