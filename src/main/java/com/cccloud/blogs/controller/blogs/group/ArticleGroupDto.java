package com.cccloud.blogs.controller.blogs.group;

import com.cccloud.blogs.entity.blogs.ArticleGroup;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.beans.BeanUtils;

/**
 * 作者：徐卫超
 * 时间：2020/6/29 9:05
 * 描述：
 */
@Data
public class ArticleGroupDto {

    /**
     *
     **/
    @ApiModelProperty("文章组ID")
    private Integer id;

    /**
     *
     **/
    @ApiModelProperty("组名称")
    private String name;

    public static ArticleGroupDto convert(ArticleGroup src) {
        ArticleGroupDto tar = new ArticleGroupDto();
        BeanUtils.copyProperties(src, tar);
        return tar;
    }
}
