package com.lyzzz.blog.controller;

import com.lyzzz.blog.entity.Tag;
import com.lyzzz.blog.service.TagService;
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
 * @creatTime: 2020/7/21  20:43
 * @author: Administrator
 * @remark:
 *
 * 标签管理接口
 *
 */
@RestController
@RequestMapping("/tag")
@Api(value = "TagController", tags = {"标签管理接口"})
public class TagController extends BaseController {

    @Autowired
    private TagService tagService;

    // 新增文章->文章标签
    @PostMapping("/filter/list")
    public R list(@RequestBody Tag tag){
        return new R<>(tagService.list(tag));
    }

    // 标签管理->标签列表
    @PostMapping("/list")
    public R findByPage(@RequestBody Tag tag, QueryPage queryPage){
        return new R<>(super.getData(tagService.list(tag, queryPage)));
    }

    // 标签文章
    @GetMapping("/{id}")
    public R findById(@PathVariable Long id){
        return new R<>(tagService.getById(id));
    }

    // 新增标签
    @PostMapping
    @Log("新增标签")
    public R add(@RequestBody Tag tag){
        try {
            tagService.add(tag);
            return new R<>();
        } catch (Exception e) {
            e.printStackTrace();
            throw new GlobalException(e.getMessage());
        }
    }

    // 更新标签
    @PutMapping
    @Log("更新标签")
    public R update(@RequestBody Tag tag) {
        try {
            tagService.update(tag);
            return new R();
        } catch (Exception e) {
            e.printStackTrace();
            throw new GlobalException(e.getMessage());
        }
    }

    // 删除标签
    @DeleteMapping("/{id}")
    @Log("删除标签")
    public R delete(@PathVariable("id") Long id) {
        try {
            tagService.delete(id);
            return new R();
        } catch (Exception e) {
            e.printStackTrace();
            throw new GlobalException(e.getMessage());
        }
    }

}
