package com.lyzzz.blog.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lyzzz.blog.entity.Comment;
import com.lyzzz.common.utils.SplineChart;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
@location：  BlogSystem\com.lyzzz.blog.dao  
@creatTime:   2020/7/18  10:44
@author:  Administrator
@remark:

*/
public interface CommentMapper extends BaseMapper<Comment> {

    @Select("select" +
            " date_format(create_time, '%Y-%m-%d') time, " +
            "count(*) num " +
            "from" +
            " tb_comment " +
            "group by " +
            "date_format(create_time, '%Y-%m-%d')")
    List<SplineChart> chart();

}