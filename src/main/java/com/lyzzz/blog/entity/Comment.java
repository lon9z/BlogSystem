package com.lyzzz.blog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
@location：  BlogSystem\com.lyzzz.blog.entity  
@creatTime:   2020/7/18  10:44
@author:  Administrator
@remark:

*/
@ApiModel(value="com-lyzzz-blog-entity-Comment")
@Data
@TableName(value = "tb_comment")
public class Comment implements Serializable {
    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value="ID")
    private Long id;

    /**
     * 文章ID
     */
    @TableField(value = "article_id")
    @ApiModelProperty(value="文章ID")
    private Long articleId;

    /**
     * 给谁留言
     */
    @TableField(value = "nickname")
    @ApiModelProperty(value="给谁留言")
    private String nickname;

    /**
     * 留言内容
     */
    @TableField(value = "content")
    @ApiModelProperty(value="留言内容")
    private String content;

    /**
     * 邮箱
     */
    @TableField(value = "email")
    @ApiModelProperty(value="邮箱")
    private String email;

    /**
     * IP地址
     */
    @TableField(value = "ip")
    @ApiModelProperty(value="IP地址")
    private String ip;

    /**
     * 设备
     */
    @TableField(value = "device")
    @ApiModelProperty(value="设备")
    private String device;

    /**
     * 地址
     */
    @TableField(value = "address")
    @ApiModelProperty(value="地址")
    private String address;

    /**
     * 留言时间
     */
    @TableField(value = "create_time")
    @ApiModelProperty(value="留言时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    private static final long serialVersionUID = 1L;
}