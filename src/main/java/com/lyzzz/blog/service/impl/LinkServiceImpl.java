package com.lyzzz.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyzzz.blog.dao.LinkMapper;
import com.lyzzz.blog.entity.Link;
import com.lyzzz.blog.service.LinkService;
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
public class LinkServiceImpl extends ServiceImpl<LinkMapper, Link> implements LinkService{

    @Autowired
    private LinkMapper linkMapper;

    @Override
    public IPage<?> list(Link link, QueryPage queryPage) {
        Page<Link> page = new Page<>(queryPage.getPage(), queryPage.getLimit());
        LambdaQueryWrapper<Link> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(link.getName()),
                          Link::getName, link.getName());
        queryWrapper.orderByDesc(Link::getId);
        return linkMapper.selectPage(page, queryWrapper);

    }

    @Override
    @Transactional
    public void add(Link link) {
        linkMapper.insert(link);
    }

    @Override
    @Transactional
    public void update(Link link) {
        this.updateById(link);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        linkMapper.deleteById(id);
    }
}
