package com.lyzzz.blog.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lyzzz.blog.entity.LoginLog;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lyzzz.common.utils.QueryPage;

/**
 * @location： BlogSystem\com.lyzzz.blog.service
 * @creatTime: 2020/7/18  10:44
 * @author: Administrator
 * @remark:
 */
public interface LoginLogService extends IService<LoginLog> {

    int saveLog(LoginLog loginLog);

    IPage<?> list(LoginLog loginLog, QueryPage queryPage);

    int delete(Long id);
}
