package com.lyzzz.blog.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lyzzz.blog.entity.Category;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lyzzz.common.utils.QueryPage;

import java.util.List;

/**
 * @location： BlogSystem\com.lyzzz.blog.service
 * @creatTime: 2020/7/18  10:44
 * @author: Administrator
 * @remark:
 */
public interface CategoryService extends IService<Category> {

    List<Category> findByArticleId(Long id);

    List<Category> list(Category category);

    IPage<Category> list(Category category, QueryPage queryPage);

    void add(Category category);

    void update(Category category);

    void delete(Long id);
}
