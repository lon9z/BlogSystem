package com.lyzzz.blog.controller;

import com.lyzzz.blog.entity.Log;
import com.lyzzz.blog.service.LogService;
import com.lyzzz.common.controller.BaseController;
import com.lyzzz.common.exception.GlobalException;
import com.lyzzz.common.utils.QueryPage;
import com.lyzzz.common.utils.R;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @location： BlogSystem\com.lyzzz.blog.controller
 * @creatTime: 2020/7/22  13:08
 * @author: Administrator
 * @remark:
 *
 * 日志管理接口
 *
 */
@RestController
@RequestMapping("/log")
@Api(value = "LogController", tags = {"日志管理接口"})
public class LogController extends BaseController {

    @Autowired
    private LogService logService;

    // 日志列表
    @PostMapping("/list")
    public R list(@RequestBody Log log, QueryPage queryPage){
        return new R<>(super.getData(logService.list(log, queryPage)));
    }

    @DeleteMapping("/{id}")
    public R delete(@PathVariable Long id){
        try {
            logService.delete(id);
            return new R<>();
        } catch (Exception e) {
            e.printStackTrace();
            throw new GlobalException(e.getMessage());
        }
    }


}
