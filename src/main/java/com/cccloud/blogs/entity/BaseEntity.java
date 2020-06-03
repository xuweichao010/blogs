package com.cccloud.blogs.entity;

import com.cccloud.blogs.easybatis.anno.auditor.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class BaseEntity implements Serializable {
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

    /**
     * 更新时间
     **/

    @UpdateTime
    private Date updateTime;

    /**
     * 更新ID
     **/
    @UpdateId
    private String updateId;

    /**
     * 更新名字
     **/
    @UpdateName
    private String updateName;
}
