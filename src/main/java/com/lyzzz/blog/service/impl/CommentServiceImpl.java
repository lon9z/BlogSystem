package com.lyzzz.blog.service.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyzzz.blog.dao.CommentMapper;
import com.lyzzz.blog.entity.Comment;
import com.lyzzz.blog.service.CommentService;
import com.lyzzz.common.utils.QueryPage;
import com.lyzzz.common.utils.SplineChart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
/**
@location：  BlogSystem\com.lyzzz.blog.service.impl  
@creatTime:   2020/7/18  10:44
@author:  Administrator
@remark:

*/
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService{

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public List<Long[]> chart() {
        List<Long[]> splineChart = new ArrayList<>();

        List<SplineChart> splineChartList = commentMapper.chart();
        if (splineChartList.size() > 0){
            splineChartList.forEach(item -> {
                if (item.getTime() != null){
                    Long[] d = {DateUtil.parse(item.getTime(), "yyyy-MM-dd").getTime(), item.getNum()};
                    splineChart.add(d);
                }
            });
        }

        return splineChart;
    }

    @Override
    public IPage<Comment> list(Comment comment, QueryPage queryPage) {
        IPage<Comment> page = new Page<>(queryPage.getPage(), queryPage.getLimit());
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(
                        StringUtils.isNotBlank(comment.getContent()),
                        Comment::getContent,
                        comment.getContent());
        queryWrapper.orderByDesc(Comment::getId);

        return commentMapper.selectPage(page, queryWrapper);
    }

    @Override
    @Transactional
    public void add(Comment comment) {
        commentMapper.insert(comment);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        commentMapper.deleteById(id);
    }

    @Override
    public List<Comment> findByArticleId(Long id) {
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Comment::getArticleId, id);
        queryWrapper.orderByDesc(Comment::getId);

        return commentMapper.selectList(queryWrapper);
    }
}
