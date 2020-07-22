package com.lyzzz.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyzzz.blog.dao.LoginLogMapper;
import com.lyzzz.blog.entity.LoginLog;
import com.lyzzz.blog.service.LoginLogService;
import com.lyzzz.common.utils.QueryPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
@location：  BlogSystem\com.lyzzz.blog.service.impl  
@creatTime:   2020/7/18  10:44
@author:  Administrator
@remark:

*/
@Service
public class LoginLogServiceImpl extends ServiceImpl<LoginLogMapper, LoginLog> implements LoginLogService{

    @Autowired
    private LoginLogMapper loginLogMapper;

    @Override
    // 开启事务
    @Transactional
    public int saveLog(LoginLog loginLog) {
        return loginLogMapper.insert(loginLog);
    }

    @Override
    public IPage<?> list(LoginLog loginLog, QueryPage queryPage) {
        Page<LoginLog> page = new Page<>(queryPage.getPage(), queryPage.getLimit());
        LambdaQueryWrapper<LoginLog> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(
                StringUtils.isNotBlank(loginLog.getLocation()),
                LoginLog::getLocation,
                loginLog.getLocation());
        queryWrapper.orderByDesc(LoginLog::getCreateTime);

        return loginLogMapper.selectPage(page, queryWrapper);
    }

    @Override
    @Transactional
    public int delete(Long id) {
        return loginLogMapper.deleteById(id);
    }
}
