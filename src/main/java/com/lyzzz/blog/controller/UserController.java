package com.lyzzz.blog.controller;

import com.lyzzz.blog.entity.User;
import com.lyzzz.blog.service.UserService;
import com.lyzzz.common.annotation.Log;
import com.lyzzz.common.controller.BaseController;
import com.lyzzz.common.exception.GlobalException;
import com.lyzzz.common.utils.R;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @location： BlogSystem\com.lyzzz.blog.controller
 * @creatTime: 2020/7/20  14:13
 * @author: Administrator
 * @remark:
 *
 * 用户功能接口
 *
 */
@RestController
@RequestMapping("/user")
@Api(value = "UserController", tags = {"用户功能接口"})
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    // 用户增长量图标信息
    @GetMapping("/chart")
    public R chart(){
        return new R<>(userService.chart());
    }

    // 基本设置->用户信息
    @GetMapping("/info")
    public R getInfo(){
        return new R<>(this.getCurrentUser());
    }

    @GetMapping("/findByName")
    public R findByName(@RequestParam(value = "username") String userName){
        return new R<>(userService.findByName(userName));
    }

    // 基本设置->修改登录账户是否重复
    @GetMapping("/checkName")
    public R checkName(@RequestParam(value = "username") String userName){
        return new R<>(userService.checkName(userName, this.getCurrentUser().getUsername()));
    }

    // 修改密码
    @PutMapping("/changePass")
    @Log("修改密码")
    public R changePass(@RequestBody User user){
        try {
            user.setId(this.getCurrentUser().getId());
            userService.changePass(user);
            return new R<>();
        } catch (Exception e) {
            e.printStackTrace();
            throw new GlobalException(e.getMessage());
        }
    }

    // 更新基本信息
    @PutMapping()
    @Log("/更新用户信息")
    public R update(@RequestBody User user){
        try {
            userService.update(user);
            return new R<>();
        } catch (Exception e) {
            e.printStackTrace();
            throw new GlobalException(e.getMessage());
        }
    }

}
