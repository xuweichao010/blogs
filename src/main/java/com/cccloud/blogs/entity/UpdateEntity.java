package com.cccloud.blogs.entity;

import com.cccloud.blogs.easybatis.anno.auditor.UpdateId;
import com.cccloud.blogs.easybatis.anno.auditor.UpdateName;
import com.cccloud.blogs.easybatis.anno.auditor.UpdateTime;

import java.io.Serializable;
import java.util.Date;

/**
 * 作者：徐卫超
 * 时间：2020/6/29 13:58
 * 描述：
 */
public class UpdateEntity implements Serializable {
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
