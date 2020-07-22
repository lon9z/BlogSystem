package com.lyzzz.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyzzz.blog.dao.ArticleCategoryMapper;
import com.lyzzz.blog.entity.ArticleCategory;
import com.lyzzz.blog.service.ArticleCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
@location：  BlogSystem\com.lyzzz.blog.service.impl  
@creatTime:   2020/7/18  10:43
@author:  Administrator
@remark:

*/
@Service
public class ArticleCategoryServiceImpl extends ServiceImpl<ArticleCategoryMapper, ArticleCategory> implements ArticleCategoryService{

    @Autowired
    private ArticleCategoryMapper articleCategoryMapper;

    @Override
    @Transactional
    public void deleteByArticleId(Long id) {
        LambdaQueryWrapper<ArticleCategory> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ArticleCategory::getArticleId, id);
        articleCategoryMapper.delete(queryWrapper);
    }

    @Override
    @Transactional
    public void add(ArticleCategory articleCategory) {
        if (!exists(articleCategory)){
            articleCategoryMapper.insert(articleCategory);
        }
    }

    // 判断是否存在
    private boolean exists(ArticleCategory articleCategory) {
        LambdaQueryWrapper<ArticleCategory> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ArticleCategory::getArticleId, articleCategory.getArticleId());
        queryWrapper.eq(ArticleCategory::getCategoryId, articleCategory.getCategoryId());

        return articleCategoryMapper.selectList(queryWrapper).size() > 0;
    }

    @Override
    @Transactional
    public void deleteByCategoryId(Long id) {
        LambdaQueryWrapper<ArticleCategory> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ArticleCategory::getId, id);
        articleCategoryMapper.delete(queryWrapper);
    }

}
