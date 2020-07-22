package com.lyzzz.blog.controller;

import com.lyzzz.blog.entity.LoginLog;
import com.lyzzz.blog.service.LoginLogService;
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
 * @creatTime: 2020/7/20  14:31
 * @author: Administrator
 * @remark:
 *
 * 登录日志接口
 *
 */
@RestController
@RequestMapping("/loginlog")
@Api(value = "LoginLogController", tags = {"登录日志管理接口"})
public class LoginLogController extends BaseController {
    @Autowired
    private LoginLogService loginLogService;

    // 登录日志列表
    @PostMapping("/list")
    public R findByPage(@RequestBody LoginLog loginLog, QueryPage queryPage){
        return new R<>(super.getData(loginLogService.list(loginLog, queryPage)));
    }

    // 删除登录日志
    @Log(value = "删除登录日志")
    @DeleteMapping("/{id}")
    public R delete(@PathVariable("id") Long id){
        try {
            loginLogService.delete(id);
            return new R();
        } catch (Exception e) {
            throw new GlobalException(e.getMessage());
        }

    }

}
