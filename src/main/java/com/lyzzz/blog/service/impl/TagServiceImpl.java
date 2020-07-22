package com.lyzzz.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyzzz.blog.dao.TagMapper;
import com.lyzzz.blog.entity.Tag;
import com.lyzzz.blog.service.ArticleTagService;
import com.lyzzz.blog.service.TagService;
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
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService{

    @Autowired
    private TagMapper tagMapper;

    @Autowired
    private ArticleTagService articleTagService;

    @Override
    public List<Tag> findByArticleId(Long id) {
        return tagMapper.findByArticleId(id);
    }

    @Override
    public List<Tag> list(Tag tag) {
        LambdaQueryWrapper<Tag> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(
                StringUtils.isNotBlank(tag.getName()),
                Tag::getName,
                tag.getName()
        );
        queryWrapper.orderByDesc(Tag::getId);

        return tagMapper.selectList(queryWrapper);
    }

    @Override
    public IPage<?> list(Tag tag, QueryPage queryPage) {
        IPage<Tag> page = new Page<>(queryPage.getPage(), queryPage.getLimit());
        LambdaQueryWrapper<Tag> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(
                StringUtils.isNotBlank(tag.getName()),
                Tag::getName,
                tag.getName()
        );
        queryWrapper.orderByDesc(Tag::getId);

        return tagMapper.selectPage(page, queryWrapper);
    }

    @Override
    @Transactional
    public void add(Tag tag) {
        if (!exists(tag)){
            tagMapper.insert(tag);
        }
    }

    private boolean exists(Tag tag) {
        LambdaQueryWrapper<Tag> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Tag::getName, tag.getName());
        return tagMapper.selectList(queryWrapper).size() > 0 ;
    }

    @Override
    @Transactional
    public void update(Tag tag) {
        tagMapper.updateById(tag);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        // 1. 删除标签信息
        tagMapper.deleteById(id);
        // 2. 删除该标签与文章关联的信息
        articleTagService.deleteByTagsId(id);

    }
}
