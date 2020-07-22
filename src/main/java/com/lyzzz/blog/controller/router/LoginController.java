package com.lyzzz.blog.controller.router;

import com.lyzzz.blog.entity.LoginLog;
import com.lyzzz.blog.entity.User;
import com.lyzzz.blog.service.LoginLogService;
import com.lyzzz.blog.service.UserService;
import com.lyzzz.common.constants.CommonConstant;
import com.lyzzz.common.controller.BaseController;
import com.lyzzz.common.exception.GlobalException;
import com.lyzzz.common.utils.*;
import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.OperatingSystem;
import eu.bitwalker.useragentutils.UserAgent;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @location： BlogSystem\com.lyzzz.blog.controller.router
 * @creatTime: 2020/7/18  12:42
 * @author: Administrator
 * @remark:
 *
 * login 接口
 *
 */
@RestController
@Api(value = "LoginController", tags = {"登录接口"})
public class LoginController extends BaseController {

    @Autowired
    private Md5Util md5Util;

    @Autowired
    private LoginLogService loginLogService;

    @Autowired
    private UserService userService;

    // 登录
    @PostMapping("/login")
    public R login(@RequestParam(value = "username", required = false) String userName,
                   @RequestParam(value = "password", required = false) String password){

        Subject subject = getSubject();
        String encryptPassword = md5Util.encryptPassword(password);
        UsernamePasswordToken token = new UsernamePasswordToken(userName, encryptPassword);
        try {
            // 登录
            subject.login(token);
            HttpServletRequest request = HttpContextUtil.getHttpServletRequest();
            // 记录登录日志
            LoginLog loginLog = new LoginLog();
            // 获取HTTP请求
            String ip = IPUtil.getIpAddr(request);
            loginLog.setIp(ip);
            loginLog.setUsername(super.getCurrentUser().getUsername());
            loginLog.setLocation(AddressUtil.getAddress(ip));
            loginLog.setCreateTime(new Date());

            String header = request.getHeader(CommonConstant.USER_AGENT);
            UserAgent userAgent = UserAgent.parseUserAgentString(header);
            Browser browser = userAgent.getBrowser();
            OperatingSystem operatingSystem = userAgent.getOperatingSystem();
            loginLog.setDevice(browser.getName() + "--" + operatingSystem.getName());
            loginLogService.saveLog(loginLog);
            request.getSession().setAttribute("user", this.getCurrentUser());
            return new R<>();
        } catch (Exception e) {
            e.printStackTrace();
            return new R<>(e);
        }
    }

    // 注册
    @PostMapping("/register")
    public R register(@RequestBody User user){

        try {
            userService.add(user);
            return new R();
        } catch (Exception e) {
            throw  new GlobalException(e.getMessage());
        }

    }




}
