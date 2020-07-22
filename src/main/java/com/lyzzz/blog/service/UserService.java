package com.lyzzz.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lyzzz.blog.entity.User;

import java.util.List;

/**
 * @location： BlogSystem\com.lyzzz.blog.service
 * @creatTime: 2020/7/18  10:45
 * @author: Administrator
 * @remark:
 */
public interface UserService extends IService<User> {


    User findByName(String userName);

    int add(User user);

    User checkName(String userName, String currentUserName);

    List<Long[]> chart();

    void changePass(User user);

    void update(User user);

}
