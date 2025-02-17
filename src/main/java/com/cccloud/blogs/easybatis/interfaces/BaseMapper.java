package com.cccloud.blogs.easybatis.interfaces;

import com.cccloud.blogs.easybatis.anno.*;

import java.util.Collection;

/**
 * 创建人：徐卫超
 * 创建时间：2019/4/2  11:19
 * 业务：
 * 功能：
 */
@SuppressWarnings("unused")
public interface BaseMapper<E, K> extends EasyMapper<E, K> {

    @SelectSql
    @ParamKey
    E selectKey(K id);

    @UpdateSql
    Long update(E entity);

    @InsertSql
    Long insert(E entity);

    @InsertSql
    Long insertBatch(Collection<E> list);

    @DeleteSql
    @ParamKey
    Long delete(K id);

}
