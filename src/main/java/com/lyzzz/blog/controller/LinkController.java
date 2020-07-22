package com.lyzzz.blog.controller;

import com.lyzzz.blog.entity.Link;
import com.lyzzz.blog.service.LinkService;
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
 * @creatTime: 2020/7/20  10:42
 * @author: Administrator
 * @remark:
 *
 * 链接接口
 *
 */
@RestController
@RequestMapping("/link")
@Api(value = "LinkController", tags = "链接管理接口")
public class LinkController extends BaseController {

    @Autowired
    private LinkService linkService;

    // 首页链接列表
    @PostMapping("/list")
    public R list(@RequestBody Link link, QueryPage queryPage){
        return new R<>(super.getData(linkService.list(link,queryPage)));

    }

    // To修改链接
    @GetMapping("/{id}")
    public R findById(@PathVariable Long id) {
        return new R<>(linkService.getById(id));
    }

    // 新增链接
    @PostMapping
    @Log("新增友链")
    public R save(@RequestBody Link link) {
        try {
            linkService.add(link);
            return new R();
        } catch (Exception e) {
            throw new GlobalException(e.getMessage());
        }
    }

    // 修改链接
    @PutMapping
    @Log("更新友链")
    public R update(@RequestBody Link link) {
        try {
            linkService.update(link);
            return new R();
        } catch (Exception e) {
            e.printStackTrace();
            throw new GlobalException(e.getMessage());
        }
    }

    // 删除链接
    @DeleteMapping("/{id}")
    @Log("删除友链")
    public R delete(@PathVariable Long id) {
        try {
            linkService.delete(id);
            return new R();
        } catch (Exception e) {
            throw new GlobalException(e.getMessage());
        }
    }




}
