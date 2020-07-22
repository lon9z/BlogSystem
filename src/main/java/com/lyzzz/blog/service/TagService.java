package com.lyzzz.blog.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lyzzz.blog.entity.Tag;
import com.lyzzz.common.utils.QueryPage;

import java.util.List;

/**
 * @location： BlogSystem\com.lyzzz.blog.service
 * @creatTime: 2020/7/18  10:44
 * @author: Administrator
 * @remark:
 */
public interface TagService extends IService<Tag> {

    List<Tag> findByArticleId(Long id);

    List<Tag> list(Tag tag);

    IPage<?> list(Tag tag, QueryPage queryPage);

    void add(Tag tag);

    void update(Tag tag);

    void delete(Long id);
}
