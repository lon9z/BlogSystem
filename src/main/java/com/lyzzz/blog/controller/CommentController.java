package com.lyzzz.blog.controller;

import com.lyzzz.blog.entity.Comment;
import com.lyzzz.blog.service.CommentService;
import com.lyzzz.common.annotation.Log;
import com.lyzzz.common.constants.CommonConstant;
import com.lyzzz.common.controller.BaseController;
import com.lyzzz.common.exception.GlobalException;
import com.lyzzz.common.utils.AddressUtil;
import com.lyzzz.common.utils.IPUtil;
import com.lyzzz.common.utils.QueryPage;
import com.lyzzz.common.utils.R;
import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.OperatingSystem;
import eu.bitwalker.useragentutils.UserAgent;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @location： BlogSystem\com.lyzzz.blog.controller
 * @creatTime: 2020/7/20  15:15
 * @author: Administrator
 * @remark:
 *
 * 评论功能接口
 *
 */
@RestController
@RequestMapping("/comment")
@Api(value = "CommentController", tags = {"评论功能接口"})
public class CommentController extends BaseController {

    @Autowired
    private CommentService commentService;

    // 首页->文章评论
    @GetMapping("/findByArticle/{id}")
    public R findByArticleId(@PathVariable Long id) {
        return new R<>(commentService.findByArticleId(id));
    }

    // 评论增长量图表
    @GetMapping("/chart")
    public R chart(){
        return new R<>(commentService.chart());
    }

    // 评论列表
    @PostMapping("/list")
    public R list(@RequestBody Comment comment, QueryPage queryPage){
        return new R<>(super.getData(commentService.list(comment, queryPage)));
    }

    @GetMapping("/{id}")
    public R findById(@PathVariable Long id) {
        return new R<>(commentService.getById(id));
    }

    @PostMapping
    public R save(@RequestBody Comment comment, HttpServletRequest request) {
        try {
            String ip = IPUtil.getIpAddr(request);
            comment.setCreateTime(new Date());
            comment.setIp(ip);
            comment.setAddress(AddressUtil.getAddress(ip));
            String header = request.getHeader(CommonConstant.USER_AGENT);
            UserAgent userAgent = UserAgent.parseUserAgentString(header);
            Browser browser = userAgent.getBrowser();
            OperatingSystem operatingSystem = userAgent.getOperatingSystem();
            comment.setDevice(browser.getName() + "," + operatingSystem.getName());
            commentService.add(comment);
            return new R();
        } catch (Exception e) {
            throw new GlobalException(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @Log("删除评论")
    public R delete(@PathVariable Long id) {
        try {
            commentService.delete(id);
            return new R();
        } catch (Exception e) {
            throw new GlobalException(e.getMessage());
        }
    }


}
