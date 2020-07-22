package com.lyzzz.blog.service;

import com.lyzzz.blog.entity.ArticleCategory;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @location： BlogSystem\com.lyzzz.blog.service
 * @creatTime: 2020/7/18  10:43
 * @author: Administrator
 * @remark:
 */
public interface ArticleCategoryService extends IService<ArticleCategory> {


    void deleteByArticleId(Long id);

    void add(ArticleCategory articleCategory);

    void deleteByCategoryId(Long id);
}
