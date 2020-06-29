package com.cccloud.blogs.entity.blogs;

import com.cccloud.blogs.easybatis.anno.table.Id;
import com.cccloud.blogs.easybatis.anno.table.Table;
import com.cccloud.blogs.easybatis.enums.IdType;
import lombok.Data;

/**
 * 作者：徐卫超
 * 时间：2020/6/29 8:54
 * 描述：文章组管理
 */

@Data
@Table("t_article_group")
public class ArticleGroup {
    /**
     * 组ID
     **/
    @Id(type = IdType.AUTO)
    private Integer id;

    /**
     * 组名称
     **/
    private String name;

    /**
     * 账户ID
     **/
    private Integer accountId;
}
