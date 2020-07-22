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
@creatTime:   2020/7/18  10:43
@author:  Administrator
@remark:

*/
@ApiModel(value="com-lyzzz-blog-entity-ArticleCategory")
@Data
@TableName(value = "tb_article_category")
public class ArticleCategory implements Serializable {
    /**
     * 编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value="编号")
    private Integer id;

    /**
     * 文章ID
     */
    @TableField(value = "article_id")
    @ApiModelProperty(value="文章ID")
    private Long articleId;

    /**
     * 分类ID
     */
    @TableField(value = "category_id")
    @ApiModelProperty(value="分类ID")
    private Long categoryId;


    public ArticleCategory(Long articleId, Long categoryId) {
        this.articleId = articleId;
        this.categoryId = categoryId;
    }

    private static final long serialVersionUID = 1L;
}