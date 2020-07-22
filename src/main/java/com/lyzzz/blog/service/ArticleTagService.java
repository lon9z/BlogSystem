package com.lyzzz.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lyzzz.blog.entity.ArticleTag;

/**
 * @location： BlogSystem\com.lyzzz.blog.service
 * @creatTime: 2020/7/18  10:43
 * @author: Administrator
 * @remark:
 */
public interface ArticleTagService extends IService<ArticleTag> {


    void deleteByArticleId(Long id);

    void add(ArticleTag articleTag);

    void deleteByTagsId(Long id);
}
