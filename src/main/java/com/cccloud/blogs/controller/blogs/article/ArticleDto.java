package com.cccloud.blogs.controller.blogs.article;

import com.cccloud.blogs.entity.blogs.Article;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.beans.BeanUtils;

/**
 * 作者：徐卫超
 * 时间：2020/6/29 14:07
 * 描述：文章描述
 */
@Data
public class ArticleDto {

    @ApiModelProperty("文章描述ID")
    private Integer id;

    @ApiModelProperty("文章所属组")
    private Integer groupId;

    @ApiModelProperty("文章标题")
    private String title;

    @ApiModelProperty("文章描述")
    private String describe;

    public static ArticleDto convert(Article src) {
        ArticleDto tar = new ArticleDto();
        BeanUtils.copyProperties(src, tar);
        return tar;
    }
}
