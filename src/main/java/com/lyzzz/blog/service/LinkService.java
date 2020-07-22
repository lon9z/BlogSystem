package com.lyzzz.blog.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lyzzz.blog.entity.Link;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lyzzz.common.utils.QueryPage;

/**
 * @location： BlogSystem\com.lyzzz.blog.service
 * @creatTime: 2020/7/18  10:44
 * @author: Administrator
 * @remark:
 */
public interface LinkService extends IService<Link> {

    IPage<?> list(Link link, QueryPage queryPage);

    void add(Link link);

    void update(Link link);

    void delete(Long id);
}
