package com.cccloud.blogs.mapper.blogs;

import com.cccloud.blogs.controller.blogs.group.ArticleGroupFilterDto;
import com.cccloud.blogs.easybatis.anno.SelectSql;
import com.cccloud.blogs.easybatis.anno.condition.Count;
import com.cccloud.blogs.easybatis.interfaces.BaseMapper;
import com.cccloud.blogs.entity.blogs.ArticleGroup;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 作者：徐卫超
 * 时间：2020/6/29 8:56
 * 描述：
 */
@Mapper
public interface ArticleGroupMapper extends BaseMapper<ArticleGroup, Long> {

    @SelectSql(dynamic = true)
    List<ArticleGroup> listBy(String accountId);

    @SelectSql
    List<ArticleGroup> list(ArticleGroupFilterDto filter);

    @SelectSql
    @Count
    Long count(ArticleGroupFilterDto filter);
}
