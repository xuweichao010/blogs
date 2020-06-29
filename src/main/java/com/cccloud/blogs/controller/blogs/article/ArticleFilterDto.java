package com.cccloud.blogs.controller.blogs.article;

import com.cccloud.blogs.commons.PageRequest;
import lombok.Data;

/**
 * 作者：徐卫超
 * 时间：2020/6/29 14:07
 * 描述：文章描述列表查询
 */
@Data
public class ArticleFilterDto extends PageRequest {

    private Long accountId;
}
