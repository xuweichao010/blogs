package com.cccloud.blogs.entity;

import com.cccloud.blogs.easybatis.anno.auditor.CreateId;
import com.cccloud.blogs.easybatis.anno.auditor.CreateName;
import com.cccloud.blogs.easybatis.anno.auditor.CreateTime;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 作者：徐卫超
 * 时间：2020/6/9 15:28
 * 描述：
 */
@Data
public class CreationEntity implements Serializable {

    /**
     * 创建时间
     **/
    @CreateTime
    private Date createTime;

    /**
     * 创建ID
     **/
    @CreateId
    private String createId;

    /**
     * 创建名字
     **/
    @CreateName
    private String createName;
}
