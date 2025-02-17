package com.cccloud.blogs.easybatis.anno.table;

import com.cccloud.blogs.easybatis.enums.IdType;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * 创建人：徐卫超
 * 创建时间：2019/1/18  15:13
 * 业务：
 * 功能：
 */

@Documented
@Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Id {
    @AliasFor("value")
    String colum() default "";

    @AliasFor("colum")
    String value() default "";

    IdType type() default IdType.AUTO;
}
