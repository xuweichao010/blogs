package com.cccloud.blogs.easybatis.enums;/** * 创建人：徐卫超 * 时间：2019/8/3 14:59 * 功能： * 备注： */public enum IdType {    /**     * 全局指定的主键  通过系统配置来指定     */    GLOBAL,    /**     * 使用自动增长主键     */    AUTO,    /**     * 使用UUID主键     */    UUID,    /**     * 自定义主键     */    CUSTOM;}