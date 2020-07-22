package com.lyzzz.blog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Data;

/**
@location：  BlogSystem\com.lyzzz.blog.entity  
@creatTime:   2020/7/18  10:44
@author:  Administrator
@remark:

*/
@ApiModel(value="com-lyzzz-blog-entity-Category")
@Data
@TableName(value = "tb_category")
public class Category implements Serializable {
    /**
     * 编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value="编号")
    private Long id;

    /**
     * 分类名称
     */
    @TableField(value = "name")
    @ApiModelProperty(value="分类名称")
    private String name;

    private static final long serialVersionUID = 1L;
}