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
public class ArticleOpenDto extends ArticleDto {

    @ApiModelProperty("创建人名字")
    private String updateName;

    @ApiModelProperty("创建人ID")
    private Integer accountId;

    public static ArticleOpenDto convert(Article src) {
        ArticleOpenDto tar = new ArticleOpenDto();
        BeanUtils.copyProperties(src, tar);
        return tar;
    }
}
