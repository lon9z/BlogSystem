package com.lyzzz.blog.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lyzzz.blog.entity.Article;
import com.lyzzz.common.utils.QueryPage;

import java.util.List;

/**
 * @location： BlogSystem\com.lyzzz.blog.service
 * @creatTime: 2020/7/18  10:27
 * @author: Administrator
 * @remark:
 */
public interface ArticleService extends IService<Article> {

    Article findById(Long id);

    IPage<Article> list(Article article, QueryPage queryPage);

    void add(Article article);

    void update(Article article);

    void delete(Long id);

    List<Article> findByTag(Long id);

    List<Article> findByCategory(Long id);
}


