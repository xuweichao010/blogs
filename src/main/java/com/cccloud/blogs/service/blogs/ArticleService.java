package com.cccloud.blogs.service.blogs;

import com.cccloud.blogs.controller.blogs.article.ArticleFilterDto;
import com.cccloud.blogs.entity.blogs.Article;
import com.cccloud.blogs.mapper.blogs.ArticleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 作者：徐卫超
 * 时间：2020/6/29 14:05
 * 描述：文章描述
 */
@Service
public class ArticleService {
    @Autowired
    private ArticleMapper articleMapper;


    public List<Article> list(ArticleFilterDto filter) {
        return articleMapper.list(filter,"12321312");
    }


    public Long count(ArticleFilterDto filter) {
        return articleMapper.count(filter);
    }
}
