package com.lyzzz.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyzzz.blog.dao.CategoryMapper;
import com.lyzzz.blog.entity.Category;
import com.lyzzz.blog.service.ArticleCategoryService;
import com.lyzzz.blog.service.CategoryService;
import com.lyzzz.common.utils.QueryPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
/**
@location：  BlogSystem\com.lyzzz.blog.service.impl  
@creatTime:   2020/7/18  10:44
@author:  Administrator
@remark:

*/
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService{

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private ArticleCategoryService articleCategoryService;

    @Override
    public List<Category> findByArticleId(Long id) {
        return categoryMapper.findCategoryByArticleId(id);
    }

    @Override
    public List<Category> list(Category category) {
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(
                StringUtils.isNotBlank(category.getName()),
                Category::getName,
                category.getName()
                );
        queryWrapper.orderByDesc(Category::getId);

        return categoryMapper.selectList(queryWrapper);
    }

    @Override
    public IPage<Category> list(Category category, QueryPage queryPage) {
        IPage<Category> page = new Page<>(queryPage.getPage(), queryPage.getLimit());
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(
                StringUtils.isNotBlank(category.getName()),
                Category::getName,
                category.getName()
        );
        queryWrapper.orderByDesc(Category::getId);

        return categoryMapper.selectPage(page, queryWrapper);
    }

    @Override
    @Transactional
    public void add(Category category) {
        if (!exists(category)) {
            categoryMapper.insert(category);
        }
    }

    private boolean exists(Category category) {
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Category::getName, category.getName());
        return categoryMapper.selectList(queryWrapper).size() > 0;
    }

    @Override
    @Transactional
    public void update(Category category) {
        categoryMapper.updateById(category);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        categoryMapper.deleteById(id);
        //删除与该分类与文章关联的信息
        articleCategoryService.deleteByCategoryId(id);
    }

}
