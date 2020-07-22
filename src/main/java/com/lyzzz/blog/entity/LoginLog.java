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
@ApiModel(value="com-lyzzz-blog-entity-LoginLog")
@Data
@TableName(value = "tb_login_log")
public class LoginLog implements Serializable {
    /**
     * 编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value="编号")
    private Long id;

    /**
     * 用户名
     */
    @TableField(value = "username")
    @ApiModelProperty(value="用户名")
    private String username;

    /**
     * IP地址
     */
    @TableField(value = "ip")
    @ApiModelProperty(value="IP地址")
    private String ip;

    /**
     * 登录地点
     */
    @TableField(value = "location")
    @ApiModelProperty(value="登录地点")
    private String location;

    /**
     * 登录时间
     */
    @TableField(value = "create_time")
    @ApiModelProperty(value="登录时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    /**
     * 登录设备
     */
    @TableField(value = "device")
    @ApiModelProperty(value="登录设备")
    private String device;

    private static final long serialVersionUID = 1L;
}