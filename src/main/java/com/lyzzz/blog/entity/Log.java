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
@ApiModel(value="com-lyzzz-blog-entity-Log")
@Data
@TableName(value = "tb_log")
public class Log implements Serializable {
    /**
     * 编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value="编号")
    private Long id;

    /**
     * 操作用户
     */
    @TableField(value = "username")
    @ApiModelProperty(value="操作用户")
    private String username;

    /**
     * 操作描述
     */
    @TableField(value = "operation")
    @ApiModelProperty(value="操作描述")
    private String operation;

    /**
     * 耗时(毫秒)
     */
    @TableField(value = "time")
    @ApiModelProperty(value="耗时(毫秒)")
    private Long time;

    /**
     * 操作方法
     */
    @TableField(value = "method")
    @ApiModelProperty(value="操作方法")
    private String method;

    /**
     * 操作参数
     */
    @TableField(value = "params")
    @ApiModelProperty(value="操作参数")
    private String params;

    /**
     * IP地址
     */
    @TableField(value = "ip")
    @ApiModelProperty(value="IP地址")
    private String ip;

    /**
     * 操作时间
     */
    @TableField(value = "create_time")
    @ApiModelProperty(value="操作时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    /**
     * 操作地点
     */
    @TableField(value = "location")
    @ApiModelProperty(value="操作地点")
    private String location;

    private static final long serialVersionUID = 1L;
}