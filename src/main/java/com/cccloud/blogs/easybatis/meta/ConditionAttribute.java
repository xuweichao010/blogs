package com.cccloud.blogs.easybatis.meta;import com.cccloud.blogs.easybatis.enums.ConditionType;import java.lang.reflect.Method;/** * 创建人：徐卫超 * 时间：2019/8/4 10:06 * 功能：查询条件封装 * 备注： */public class ConditionAttribute extends Attribute {    private ConditionType type;    private int index;    public ConditionAttribute(Attribute attribute, int index, ConditionType type) {        super(attribute.getField(), attribute.getColunm()                , attribute.getGetter(), attribute.getSetter());        this.type = type;        this.index = index;    }    public ConditionAttribute(String field, String colunm, Method getter, Method setter, int index, ConditionType type) {        super(field, colunm, getter, setter);        this.type = type;        this.index = index;    }    public int index() {        return index;    }    public ConditionType getType() {        return type;    }}