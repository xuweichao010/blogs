package com.cccloud.blogs.controller.blogs.group;

import com.cccloud.blogs.commons.PageRequest;
import lombok.Data;

/**
 * 作者：徐卫超
 * 时间：2020/6/29 9:10
 * 描述：
 */
@Data
public class ArticleGroupFilterDto extends PageRequest {
    private Long accountId;
}
