package com.cccloud.blogs.mapper.system;

import com.cccloud.blogs.easybatis.interfaces.BaseMapper;
import com.cccloud.blogs.entity.system.Access;
import org.apache.ibatis.annotations.Mapper;

/**
 * 作者：徐卫超
 * 时间：2020/6/4 8:37
 * 描述：
 */
@Mapper
public interface AccessMapper extends BaseMapper<Access, Integer> {
}
