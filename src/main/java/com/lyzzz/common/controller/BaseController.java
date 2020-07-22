package com.lyzzz.common.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lyzzz.blog.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import java.util.HashMap;
import java.util.Map;

/**
 * @location： BlogSystem\com.lyzzz.common.controller
 * @creatTime: 2020/7/18  12:44
 * @author: Administrator
 * @remark:
 *
 * controller层公共方法抽取
 *
 */

public class BaseController {

    protected static Subject getSubject(){
        return SecurityUtils.getSubject();
    }

    // 获取当前用户
    protected User getCurrentUser(){
        return (User) getSubject().getPrincipal();
    }

    protected Session getSession(){
        return getSubject().getSession();
    }

    protected Session getSession(Boolean flag){
        return getSubject().getSession(flag);
    }

    protected void login(AuthenticationToken token){
        getSubject().login(token);
    }

    public Map<String, Object> getData(IPage<?> page){
        Map<String, Object> data = new HashMap<>();
        data.put("rows", page.getRecords());
        data.put("total", page.getTotal());
        return data;

    }

    public Map<String, Object> getToken(){
        Map<String, Object> map = new HashMap<>();
        map.put("token", getSession().getId());
        return map;

    }





}
