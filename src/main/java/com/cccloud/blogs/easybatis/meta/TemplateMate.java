package com.cccloud.blogs.easybatis.meta;import com.cccloud.blogs.easybatis.enums.GroupType;/** * 创建人：徐卫超 * 时间：2019/8/4 10:22 * 功能： * 备注： */public class TemplateMate {    private String template;    private GroupType type;    public TemplateMate(String template, GroupType type) {        this.template = template;        this.type = type;    }    public String getTemplate() {        return template;    }    public GroupType getType() {        return type;    }}