package com.lyzzz.blog.controller.router;

import com.lyzzz.blog.entity.User;
import com.lyzzz.blog.service.ArticleService;
import com.lyzzz.blog.service.CommentService;
import com.lyzzz.blog.service.TagService;
import com.lyzzz.blog.service.UserService;
import com.lyzzz.common.controller.BaseController;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;

/**
 * @location： BlogSystem\com.lyzzz.blog.controller.router
 * @creatTime: 2020/7/18  14:36
 * @author: Administrator
 * @remark:
 *
 * 后台页面路由
 *
 */
// 加注解在类或者方法上 表示在swagger页面,忽略掉这个类或者方法
@ApiIgnore
@Controller
public class BackRouterController extends BaseController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private TagService tagService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    // 登录状态校验
    private boolean auth(HttpServletRequest request, Model model){
        Object o = request.getSession().getAttribute("user");
        model.addAttribute("user",o);
        return o instanceof User;
    }


    // 登出
    @GetMapping("/logout")
    public String logout(){
        Subject subject = getSubject();
        subject.logout();
        return "redirect:/login";
    }

    // 登录
    @GetMapping("/login")
    public String login(HttpServletRequest request){
        request.getSession().removeAttribute("user");
        return "login";
    }

    // 注册
    @GetMapping("/register")
    public String register(HttpServletRequest request){
        request.getSession().removeAttribute("user");
        return "register";
    }

    // 首页
    @GetMapping("/blog")
    public String index(HttpServletRequest request, Model model){
        if (!this.auth(request, model)){
            return "redirect:/login";
        }
        model.addAttribute("articleCount", articleService.count());
        model.addAttribute("tagCount", tagService.count());
        model.addAttribute("commentCount", commentService.count());
        model.addAttribute("userCount", userService.count());

        return "blog/index/index";
    }

    // 账户设置
    @GetMapping("/blog/profile")
    public String profile(HttpServletRequest request, Model model){
        if (!this.auth(request, model)){
            return "redirect:/login";
        }
        return "blog/profile/index";

    }

    // 发布文章
    @GetMapping("/blog/article/write")
    public String articleWrite(HttpServletRequest request, Model model) {
        if (!this.auth(request, model)) {
            return "redirect:/login";
        }
        return "blog/article/write/index";
    }

    // 文章列表
    @GetMapping("/blog/article/list")
    public String articleList(HttpServletRequest request, Model model) {
        if (!this.auth(request, model)) {
            return "redirect:/login";
        }
        return "blog/article/list/index";
    }

    // 标签管理
    @GetMapping("/blog/blog/tag")
    public String blogTag(HttpServletRequest request, Model model) {
        if (!this.auth(request, model)) {
            return "redirect:/login";
        }
        return "blog/blog/tag/index";
    }

    // 分类管理
    @GetMapping("/blog/blog/category")
    public String blogCategory(HttpServletRequest request, Model model) {
        if (!this.auth(request, model)) {
            return "redirect:/login";
        }
        return "blog/blog/category/index";
    }

    // 友情链接管理
    @GetMapping("/blog/blog/link")
    public String blogLink(HttpServletRequest request, Model model) {
        if (!this.auth(request, model)) {
            return "redirect:/login";
        }
        return "blog/blog/link/index";
    }

    // 评论管理
    @GetMapping("/blog/blog/comment")
    public String blogComment(HttpServletRequest request, Model model) {
        if (!this.auth(request, model)) {
            return "redirect:/login";
        }
        return "blog/blog/comment/index";
    }

    // 操作日志
    @GetMapping("/blog/setting/log")
    public String settingLog(HttpServletRequest request, Model model) {
        if (!this.auth(request, model)) {
            return "redirect:/login";
        }
        return "blog/setting/log/index";
    }

    // 七牛云
    @GetMapping("/blog/setting/qiniu")
    public String settingQiniu(HttpServletRequest request, Model model) {
        if (!this.auth(request, model)) {
            return "redirect:/login";
        }
        return "blog/setting/qiniu/index";
    }

    // swagger2
    @GetMapping("/blog/setting/swagger")
    public String settingSwagger(HttpServletRequest request, Model model) {
        if (!this.auth(request, model)) {
            return "redirect:/login";
        }
        return "blog/setting/swagger/index";
    }





}
