package com.lyzzz.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyzzz.blog.dao.ArticleTagMapper;
import com.lyzzz.blog.entity.ArticleTag;
import com.lyzzz.blog.service.ArticleTagService;
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
public class ArticleTagServiceImpl extends ServiceImpl<ArticleTagMapper, ArticleTag> implements ArticleTagService{

    @Autowired
    private ArticleTagMapper articleTagMapper;

    @Override
    @Transactional
    public void deleteByArticleId(Long id) {
        LambdaQueryWrapper<ArticleTag> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ArticleTag::getArticleId, id);
        articleTagMapper.delete(queryWrapper);

    }

    @Override
    @Transactional
    public void add(ArticleTag articleTag) {
        if (!exists(articleTag)){
            articleTagMapper.insert(articleTag);
        }
    }

    // 判断是否存在
    private boolean exists(ArticleTag articleTag) {
        LambdaQueryWrapper<ArticleTag> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ArticleTag::getArticleId, articleTag.getArticleId());
        queryWrapper.eq(ArticleTag::getTagId, articleTag.getTagId());

        return articleTagMapper.selectList(queryWrapper).size() > 0;
    }

    @Override
    @Transactional
    public void deleteByTagsId(Long id) {
        LambdaQueryWrapper<ArticleTag> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ArticleTag::getTagId, id);
        articleTagMapper.delete(queryWrapper);

    }
}
