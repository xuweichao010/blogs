package com.cccloud.blogs.entity.blogs;

import com.cccloud.blogs.easybatis.anno.table.Id;
import com.cccloud.blogs.easybatis.anno.table.Table;
import com.cccloud.blogs.easybatis.enums.IdType;
import com.cccloud.blogs.entity.UpdateEntity;
import lombok.Data;

import java.io.Serializable;

/**
 * 作者：徐卫超
 * 时间：2020/6/29 13:56
 * 描述：
 */
@Data
@Table("t_article")
public class Article extends UpdateEntity implements Serializable {

    /**
     * 文章ID
     **/
    @Id(type = IdType.AUTO)
    private Integer id;

    /**
     * 文章所属组
     **/
    private Integer groupId;

    /**
     * 创建人ID
     **/
    private Integer accountId;

    /**
     * 文章标题
     **/
    private String title;

    /**
     * 文章描述
     **/
    private String describe;

}
