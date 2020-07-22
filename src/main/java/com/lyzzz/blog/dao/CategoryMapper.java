package com.lyzzz.blog.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lyzzz.blog.entity.Category;

import java.util.List;

/**
@location：  BlogSystem\com.lyzzz.blog.dao  
@creatTime:   2020/7/18  10:44
@author:  Administrator
@remark:

*/
public interface CategoryMapper extends BaseMapper<Category> {

    List<Category> findCategoryByArticleId(Long id);
}