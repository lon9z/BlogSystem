package com.lyzzz.blog.controller;

import com.lyzzz.blog.entity.Article;
import com.lyzzz.blog.service.ArticleService;
import com.lyzzz.common.annotation.Log;
import com.lyzzz.common.controller.BaseController;
import com.lyzzz.common.exception.GlobalException;
import com.lyzzz.common.utils.QueryPage;
import com.lyzzz.common.utils.R;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @location： BlogSystem\com.lyzzz.blog.controller
 * @creatTime: 2020/7/18  15:21
 * @author: Administrator
 * @remark:
 *
 * 文章功能接口
 *
 */
@RestController
@RequestMapping("/article")
@Api(value = "ArticleController", tags = {"文章功能接口"})
public class ArticleController extends BaseController {
    @Autowired
    private ArticleService articleService;

    // 文章列表
    @PostMapping("/list")
    public R list(@RequestBody Article article, QueryPage queryPage){
        return new R<>(super.getData(articleService.list(article, queryPage)));
    }

    // 新增文章
    @PostMapping
    @Log("新增文章")
    public R add(@RequestBody Article article){
        try {
            article.setAuthor(this.getCurrentUser().getUsername());
            articleService.add(article);
            return new R<>();
        } catch (Exception e) {
            throw new GlobalException(e.getMessage());
        }
    }

    // 1. 跳转修改文章
    @GetMapping("/{id}")
    public R findById(@PathVariable("id") Long id){
        return new R<>(articleService.findById(id));
    }

    // 2. 修改文章
    @PutMapping()
    @Log("修改文章")
    public R update(@RequestBody Article article){
        try {
            articleService.update(article);
            return new R<>();
        } catch (Exception e) {
            e.printStackTrace();
            throw new GlobalException(e.getMessage());
        }
    }

    // 删除文章
    @DeleteMapping("/{id}")
    @Log("删除文章")
    public R delete(@PathVariable("id") Long id){
        try {
            articleService.delete(id);
            return new R<>();
        } catch (Exception e) {
            e.printStackTrace();
            throw new GlobalException(e.getMessage());
        }
    }

    // 标签管理->标签文章
    @GetMapping("/findByTag/{id}")
    public R findByTag(@PathVariable("id") Long id){
        return new R<>(articleService.findByTag(id));
    }

    // 分类管理->分类文章
    @GetMapping("/findByCategory/{id}")
    public R findByCategory(@PathVariable("id") Long id){
        return new R<>(articleService.findByCategory(id));
    }

}
