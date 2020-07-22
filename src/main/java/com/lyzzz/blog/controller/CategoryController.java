package com.lyzzz.blog.controller;

import com.lyzzz.blog.entity.Category;
import com.lyzzz.blog.service.CategoryService;
import com.lyzzz.common.annotation.Log;
import com.lyzzz.common.controller.BaseController;
import com.lyzzz.common.exception.GlobalException;
import com.lyzzz.common.utils.QueryPage;
import com.lyzzz.common.utils.R;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @location： BlogSystem\com.lyzzz.blog.controller
 * @creatTime: 2020/7/21  20:53
 * @author: Administrator
 * @remark:
 *
 * 文章类别管理接口
 *
 */
@RestController
@RequestMapping("/category")
@Api(value = "CategoryController", tags = {"文章分类管理接口"})
public class CategoryController extends BaseController {

    @Autowired
    private CategoryService categoryService;

    // 发布文章->文章分类
    @PostMapping("/filter/list")
    public R list(@RequestBody Category category){
        return new R<>(categoryService.list(category));
    }

    // 分类管理->分类列表
    @PostMapping("/list")
    public R<Map<String, Object>> list(@RequestBody Category category, QueryPage queryPage) {
        return new R<>(super.getData(categoryService.list(category, queryPage)));
    }

    // 分类文章
    @GetMapping("/{id}")
    public R<Category> findById(@PathVariable("id") Long id) {
        return new R<>(categoryService.getById(id));
    }

    // 新增分类
    @PostMapping
    @Log("新增分类")
    public R save(@RequestBody Category category) {
        try {
            categoryService.add(category);
            return new R();
        } catch (Exception e) {
            throw new GlobalException(e.getMessage());
        }
    }

    // 更新分类
    @PutMapping
    @Log("更新分类")
    public R update(@RequestBody Category category) {
        try {
            categoryService.update(category);
            return new R();
        } catch (Exception e) {
            throw new GlobalException(e.getMessage());
        }
    }

    // 删除分类
    @DeleteMapping("/{id}")
    @Log("删除分类")
    public R delete(@PathVariable Long id) {
        try {
            categoryService.delete(id);
            return new R();
        } catch (Exception e) {
            throw new GlobalException(e.getMessage());
        }
    }

}
