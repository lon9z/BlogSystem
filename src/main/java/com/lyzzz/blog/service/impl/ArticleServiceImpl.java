package com.lyzzz.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyzzz.blog.dao.ArticleMapper;
import com.lyzzz.blog.entity.*;
import com.lyzzz.blog.service.*;
import com.lyzzz.common.utils.QueryPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @location： BlogSystem\com.lyzzz.blog.service.impl
 * @creatTime: 2020/7/18  10:27
 * @author: Administrator
 * @remark:
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private TagService tagService;

    @Autowired
    private ArticleCategoryService articleCategoryService;

    @Autowired
    private ArticleTagService articleTagService;


    /**
     * @author Administrator
     * @date 2020/7/18 15:27
     * @param [list]
     * @return void
     *
     * 封装文章分类、标签数据
     *
     **/
    private void findInit(List<Article> list){
        if (!list.isEmpty()){
            list.forEach(article -> {
                List<Category> categoryList = categoryService.findByArticleId(article.getId());
                if (categoryList.size() > 0){
                    article.setCategory(categoryList.get(0));
                }
                List<Tag> tagList = tagService.findByArticleId(article.getId());
                article.setTags(tagList);
            });
        }
    }

    @Override
    public Article findById(Long id) {
        Article article = articleMapper.selectById(id);
        if (article != null){
            List<Article> articleList = new ArrayList<>();
            articleList.add(article);
            // 封装文章分类、标签数据
            findInit(articleList);
            return article;
        }
        return null;
    }

    @Override
    public IPage<Article> list(Article article, QueryPage queryPage) {
        IPage<Article> page = new Page<>(queryPage.getPage(), queryPage.getLimit());

        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapper.orderByDesc(Article::getId);

        queryWrapper.like(StringUtils.isNotBlank(article.getTitle()),
                Article::getTitle, article.getTitle());

        queryWrapper.like(StringUtils.isNotBlank(article.getAuthor()),
                Article::getAuthor, article.getAuthor());

        IPage<Article> selectPage = articleMapper.selectPage(page, queryWrapper);
        findInit(selectPage.getRecords());

        return  selectPage;

    }

    @Override
    @Transactional
    public void add(Article article) {
        article.setCreateTime(new Date());
        // 1. 保存文章
        articleMapper.insert(article);
        // 2. 保存文章->分类/标签
        this.updateArticleCategoryTags(article);
    }

    /**
     * 更新文章-类别-标签的关联
     *
     * */
    private void updateArticleCategoryTags(Article article) {
        if (article.getId() != 0){
            if (article.getCategory() != null){
                articleCategoryService.deleteByArticleId(article.getId());
                Category category = categoryService.getById(article.getCategory());
                if (category != null){
                    articleCategoryService.add(new ArticleCategory(article.getId(), category.getId()));
                }
            }
            if (article.getTags() != null && article.getTags().size() > 0){
                articleTagService.deleteByArticleId(article.getId());
                article.getTags().forEach(tag -> {
                    articleTagService.add(new ArticleTag(article.getId(), tag.getId()));
                });
            }
        }
    }

    @Override
    @Transactional
    public void update(Article article) {
        // 1. 更新文章
        articleMapper.updateById(article);
        // 2. 跟新文章分类、标签
        updateArticleCategoryTags(article);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (id != null && id != 0){
            // 1. 删除文章
            articleMapper.deleteById(id);
            // 2. 删除文章分类
            articleCategoryService.deleteByArticleId(id);
            // 3. 删除文章标签
            articleTagService.deleteByArticleId(id);
        }
    }

    @Override
    public List<Article> findByTag(Long id) {
        return articleMapper.findByTag(id);
    }

    @Override
    public List<Article> findByCategory(Long id) {
        return articleMapper.findByCategory(id);
    }


}


