package com.lyzzz.blog.controller.router;

import com.lyzzz.blog.entity.Article;
import com.lyzzz.blog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @location： BlogSystem\com.lyzzz.blog.controller.router
 * @creatTime: 2020/7/18  14:36
 * @author: Administrator
 * @remark:
 *
 * 前台页面路由
 *
 */
// 加注解在类或者方法上 表示在swagger页面,忽略掉这个类或者方法
@ApiIgnore
@Controller
public class FrontRouterController {
    @Autowired
    private ArticleService articleService;

    // 首页
    @GetMapping("/")
    public String index(){
        return "index/index";
    }

    // 文章列表查看更多
    @GetMapping("/p/{id}")
    public String getPage(@PathVariable("id") Long id, Model model){
        Article article = articleService.findById(id);
        if (article != null){
            model.addAttribute("p",article);
        }else {
            return "error/404";
        }

        return "p/index";

    }

    // 关于页面
    @GetMapping("/about")
    public String about(){
        return "about/index";
    }

    //链接页面
    @GetMapping("/links")
    public String links(){
        return "link/index";
    }

}
