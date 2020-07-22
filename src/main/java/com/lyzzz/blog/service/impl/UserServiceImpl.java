package com.lyzzz.blog.service.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyzzz.blog.dao.UserMapper;
import com.lyzzz.blog.entity.User;
import com.lyzzz.blog.service.UserService;
import com.lyzzz.common.utils.Md5Util;
import com.lyzzz.common.utils.SplineChart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
@location：  BlogSystem\com.lyzzz.blog.service.impl  
@creatTime:   2020/7/18  10:45
@author:  Administrator
@remark:

*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService{

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private Md5Util md5Util;

    @Override
    public User findByName(String userName) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, userName);
        List<User> userList = userMapper.selectList(queryWrapper);
        return userList.size() > 0 ? userList.get(0) : null;
    }

    @Override
    @Transactional
    public int add(User user) {
        String encryptPassword = md5Util.encryptPassword(user.getPassword()); // 加密
        user.setPassword(encryptPassword);
        user.setAvatar("/img/avatar/default.jpg");
        user.setCreateTime(new Date());
        return userMapper.insert(user);

    }

    @Override
    public User checkName(String userName, String currentUserName) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, userName);
        queryWrapper.ne(User::getUsername, currentUserName);
        List<User> userList = userMapper.selectList(queryWrapper);
        return userList.size() > 0 ? userList.get(0) : null;

    }

    @Override
    public List<Long[]> chart() {

        List<Long[]> splineChart = new ArrayList<>();

        List<SplineChart> splineChartList = userMapper.chart();
        if (splineChartList.size() > 0){
            splineChartList.forEach(item -> {
                if (item.getTime() != null){
                    Long[] d = {DateUtil.parse(item.getTime(), "yyyy-MM-dd").getTime(), item.getNum()};
                    splineChart.add(d);
                }
            });
        }

        return splineChart;
    }

    @Override
    @Transactional
    public void changePass(User user) {
        String encryptPassword = md5Util.encryptPassword(user.getPassword());
        user.setPassword(encryptPassword);
        userMapper.updateById(user);

    }

    @Override
    @Transactional
    public void update(User user) {
        user.setPassword(null);
        userMapper.updateById(user);

    }
}
