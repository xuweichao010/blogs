package com.cccloud.blogs.easybatis.anno.condition.filter;

import com.cccloud.blogs.easybatis.enums.ConditionType;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * 创建人：徐卫超
 * 创建时间：2019/4/24  13:01
 * 业务：空查询
 * 功能：
 */
@Documented
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Condition(type = ConditionType.NOT_NULL)
public @interface NotNull {

    /**
     * 查询条件排序
     *
     * @return
     */
    int index() default 99;

    /**
     * 关联的数据库的列  默认使用属性名
     *
     * @return
     */
    @AliasFor("value")
    String colum() default "";


    /**
     * 关联的数据库的列  默认使用属性名
     *
     * @return
     */
    @AliasFor("colum")
    String value() default "";

}
