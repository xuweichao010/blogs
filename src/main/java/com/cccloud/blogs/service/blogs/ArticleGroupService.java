package com.cccloud.blogs.service.blogs;

import com.cccloud.blogs.controller.blogs.group.ArticleGroupFilterDto;
import com.cccloud.blogs.entity.blogs.ArticleGroup;
import com.cccloud.blogs.mapper.blogs.ArticleGroupMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 作者：徐卫超
 * 时间：2020/6/29 8:47
 * 描述：博客组管理
 */
@Service
public class ArticleGroupService {

    @Autowired
    private ArticleGroupMapper articleGroupMapper;

    /**
     * 根据用户信息获取用户建立的组
     *
     * @param filter
     * @return
     */
    public List<ArticleGroup> list(ArticleGroupFilterDto filter) {
        return articleGroupMapper.list(filter);
    }

    public Long count(ArticleGroupFilterDto filter) {
        return articleGroupMapper.count(filter);
    }
}
