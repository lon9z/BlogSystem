package com.lyzzz.blog.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lyzzz.blog.entity.Comment;
import com.lyzzz.common.utils.QueryPage;

import java.util.List;

/**
 * @location： BlogSystem\com.lyzzz.blog.service
 * @creatTime: 2020/7/18  10:44
 * @author: Administrator
 * @remark:
 */
public interface CommentService extends IService<Comment> {


    List<Long[]> chart();

    IPage<Comment> list(Comment comment, QueryPage queryPage);

    void delete(Long id);

    void add(Comment comment);

    List<Comment> findByArticleId(Long id);
}
