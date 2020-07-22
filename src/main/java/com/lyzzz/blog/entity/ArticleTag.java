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
@ApiModel(value="com-lyzzz-blog-entity-ArticleTag")
@Data
@TableName(value = "tb_article_tag")
public class ArticleTag implements Serializable {
    /**
     * 编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value="编号")
    private Long id;

    /**
     * 文章ID
     */
    @TableField(value = "article_id")
    @ApiModelProperty(value="文章ID")
    private Long articleId;

    /**
     * 标签ID
     */
    @TableField(value = "tag_id")
    @ApiModelProperty(value="标签ID")
    private Long tagId;

    public ArticleTag(Long articleId, Long tagId) {
        this.articleId = articleId;
        this.tagId = tagId;
    }

    private static final long serialVersionUID = 1L;
}