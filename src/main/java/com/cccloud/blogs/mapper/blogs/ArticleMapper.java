package com.cccloud.blogs.mapper.blogs;

import com.cccloud.blogs.controller.blogs.article.ArticleFilterDto;
import com.cccloud.blogs.easybatis.anno.SelectSql;
import com.cccloud.blogs.easybatis.anno.condition.Count;
import com.cccloud.blogs.easybatis.interfaces.BaseMapper;
import com.cccloud.blogs.entity.blogs.Article;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 作者：徐卫超
 * 时间：2020/6/29 14:04
 * 描述：文章描述
 */
@Mapper
public interface ArticleMapper extends BaseMapper<Article, Long> {
    @SelectSql
    List<Article> list(ArticleFilterDto filter);

    @SelectSql
    @Count
    Long count(ArticleFilterDto filter);
}
