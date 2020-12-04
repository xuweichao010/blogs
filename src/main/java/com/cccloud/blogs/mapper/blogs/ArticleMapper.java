package com.cccloud.blogs.mapper.blogs;

import com.cccloud.blogs.controller.blogs.article.ArticleFilterDto;
import com.cccloud.blogs.easybatis.anno.SelectSql;
import com.cccloud.blogs.easybatis.anno.condition.Count;
import com.cccloud.blogs.easybatis.interfaces.BaseMapper;
import com.cccloud.blogs.entity.blogs.Article;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 作者：徐卫超
 * 时间：2020/6/29 14:04
 * 描述：文章描述
 */
@Mapper
public interface ArticleMapper extends BaseMapper<Article, Long> {

    @Select("SELECT * FROM t_article WHERE account_id = #{filter.accountId} AND group_id = #{groupId}")
    List<Article> list(ArticleFilterDto filter,String groupId);

    @SelectSql
    @Count
    Long count(ArticleFilterDto filter);
}
