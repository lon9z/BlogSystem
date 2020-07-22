package com.lyzzz.blog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
@location：  BlogSystem\com.lyzzz.blog.entity  
@creatTime:   2020/7/18  10:44
@author:  Administrator
@remark:

*/
@ApiModel(value="com-lyzzz-blog-entity-Link")
@Data
@TableName(value = "tb_link")
public class Link implements Serializable {
    /**
     * 编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value="编号")
    private Long id;

    /**
     * 连接名称
     */
    @TableField(value = "name")
    @ApiModelProperty(value="链接名称")
    private String name;

    /**
     * 连接URL
     */
    @TableField(value = "url")
    @ApiModelProperty(value="链接URL")
    private String url;

    private static final long serialVersionUID = 1L;
}