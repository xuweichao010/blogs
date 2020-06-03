package com.cccloud.blogs.easybatis.assistant;import com.cccloud.blogs.easybatis.anno.ParamKey;import com.cccloud.blogs.easybatis.anno.condition.Count;import com.cccloud.blogs.easybatis.anno.condition.Distinct;import com.cccloud.blogs.easybatis.enums.ConditionType;import com.cccloud.blogs.easybatis.interfaces.SQLAssistant;import com.cccloud.blogs.easybatis.interfaces.SyntaxTemplate;import com.cccloud.blogs.easybatis.meta.*;import java.util.List;/** * 创建人：徐卫超 * 时间：2019/8/15 17:28 * 功能： * 备注： */public class MySQLAssistan extends BaseSqlAssistan implements SQLAssistant {    private SyntaxTemplate syntaxTemplate;    public MySQLAssistan(SyntaxTemplate syntaxTemplate) {        this.syntaxTemplate = syntaxTemplate;    }    @Override    public StringBuilder delete(EntityMate entity, ConditionMate condition, boolean isObject, boolean isDynamic, ParamKey key) {        if (key != null) {            isDynamic = false;            condition.queryClear();            condition.addQuery(new ConditionAttribute(entity.getId(), 0, ConditionType.EQUEL));        }        StringBuilder sb = new StringBuilder();        sb.append("<script> ");        sb.append("DELETE FROM ").append(entity.getTableName());        sb.append(builderSelectSql(condition.queryList(), isObject, isDynamic, entity.getLogic()));        sb.append("</script> ");        return sb;    }    @Override    public StringBuilder select(EntityMate entity, ConditionMate condition, boolean isObject, boolean isDynamic, Count count, Distinct distinct, ParamKey key) {        if (key != null) {            isDynamic = false;            condition.queryClear();            condition.addQuery(new ConditionAttribute(entity.getId(), 0, ConditionType.EQUEL));        }        StringBuilder sb = new StringBuilder();        sb.append("<script> ");        sb.append("SELECT ").append(this.selectCloums(entity.selectAttribute(), count, distinct)).append("FROM ").append(entity.getTableName());        sb.append(builderSelectSql(condition.queryList(), isObject, isDynamic, entity.getLogic()));        if (count == null) {            sb.append(builderOrder(condition));            sb.append(builderPage(condition));        }        sb.append("</script> ");        return sb;    }    private StringBuilder builderPage(ConditionMate condition) {        StringBuilder sb = new StringBuilder();        if (!condition.isPage()) return sb;        TemplateMate template = syntaxTemplate.template(ConditionType.LIMIT);        sb.append(String.format(template.getTemplate(),                condition.getStart().getBatisField(null),                condition.getOffset().getBatisField(null)));        return sb;    }    private StringBuilder builderOrder(ConditionMate condition) {        StringBuilder sb = new StringBuilder();        OrderConditionAttribute order = condition.getOrder();        if (order == null) return sb;        String orderCloums;        if(order.getBy().isEmpty()){            orderCloums =  order.isByValue() ? order.getBatisField(null) : order.getColunm();        }else {            orderCloums = order.getBy();        }        return sb.append("ORDER BY ").append(orderCloums).append(" ").append(order.getOrderEnum().value());    }    @Override    public StringBuilder insert(EntityMate entity, boolean isBatch) {        if (isBatch) {            return insertBatch(entity);        } else {            return insert(entity);        }    }    public StringBuilder insertBatch(EntityMate entity) {        StringBuilder sb = new StringBuilder();        sb.append("<script> ");        sb.append("INSERT INTO ").append(entity.getTableName());        sb.append("(").append(this.insertCloums(entity.insertAttribute()))                .append(") VALUES <foreach collection='list' item='item' index='index' separator=',' >")                .append("(").append(this.insertField(entity.insertAttribute(), "item")).append(") ")                .append("</foreach>");        sb.append("</script> ");        return sb;    }    public StringBuilder insert(EntityMate entity) {        StringBuilder sb = new StringBuilder();        sb.append("<script> ");        sb.append("INSERT INTO ").append(entity.getTableName());        sb.append("(").append(this.insertCloums(entity.insertAttribute()))                .append(") VALUES (")                .append(this.insertField(entity.insertAttribute(), null)).append(") ");        sb.append("</script> ");        return sb;    }    @Override    public StringBuilder update(EntityMate entity, ConditionMate condition, boolean isUpdateParam, boolean isDynamic) {        StringBuilder sb = new StringBuilder();        if (!isUpdateParam) {            isDynamic = false;            condition.queryClear();            condition.addQuery(new ConditionAttribute(entity.getId(), 0, ConditionType.EQUEL));            entity.updateAttribute().forEach(attr -> condition.addUpdte(attr));        }else if(condition.loglic() != null){            entity.updateAuditorAttributeList().forEach(auditor->condition.addUpdte(auditor));        }        sb.append("<script> ");        sb.append("UPDATE ").append(entity.getTableName()).append("SET ");        if (condition.updateAttributeList().isEmpty() && condition.loglic() == null) {            throw new RuntimeException("没有更新条件");        }        sb.append(update(condition.updateAttributeList(), condition.loglic()));        sb.append(builderSelectSql(condition.queryList(), false, isDynamic, entity.getLogic()));        sb.append("</script> ");        return sb;    }    private String update(List<Attribute> attributeList, LoglicAttribute loglic) {        StringBuilder sb = new StringBuilder();        if (loglic == null) {            attributeList.forEach(attr -> sb                    .append(", ")                    .append(attr.getColunm()).append("=")                    .append(attr.getBatisField(null)));            return sb.substring(1) + " ";        } else {            attributeList.forEach(attr -> sb                    .append(", ")                    .append(attr.getColunm()).append("=")                    .append(attr.getBatisField(null)));            return sb.append(", ").append(loglic.getColunm()).append("=")                    .append(loglic.getInvalid()).append(" ").substring(1) + " ";        }    }    /**     * 构建查询条件     *     * @param list     * @param isObject     * @param isDynamic     * @return     */    private StringBuilder builderSelectSql(List<ConditionAttribute> list, boolean isObject, boolean isDynamic, LoglicAttribute loglic) {        StringBuilder sb = new StringBuilder();        if (isObject) {            sb.append("<where> ");            list.forEach(cond -> sb.append(builderQueryObjectSql(cond)));            if (loglic != null)                sb.append("AND ").append(loglic.getColunm()).append("=").append(loglic.getValid()).append(" ");            sb.append("</where> ");            return sb;        } else {            list.forEach(cond -> sb.append("AND ").append(builderQueryParamSql(cond, isDynamic)));            if (loglic != null)                sb.append("AND ").append(loglic.getColunm()).append("=").append(loglic.getValid()).append(" ");            StringBuilder sb2 = new StringBuilder(sb.substring(sb.length() > 4 ? 4 : 0));            if (sb2.length() != 0) {                sb2.insert(0, "WHERE ");            }            return sb2;        }    }    /**     * 查询对象来构建动态SQL     *     * @param cond     * @return     */    private String builderQueryObjectSql(ConditionAttribute cond) {        return String.format(SyntaxTemplate.TMPLATE_IF, cond.getField(), SyntaxTemplate.AND + querySql(cond));    }    /**     * 方法参数来构建动态SQL     *     * @param cond     * @param isDynamic     * @return     */    private String builderQueryParamSql(ConditionAttribute cond, boolean isDynamic) {        String sql = querySql(cond);        if (isDynamic) {            sql = String.format(SyntaxTemplate.TMPLATE_PRARM_IS_NULL, cond.getBatisField(null), sql);        }        return sql;    }    /**     * 获取查询条件     *     * @param cond     * @return     */    private String querySql(ConditionAttribute cond) {        TemplateMate template = syntaxTemplate.template(cond.getType());        String sql;        switch (template.getType()) {            case CLOUM_FIELD:                sql = String.format(template.getTemplate(),                        cond.getColunm(),                        cond.getBatisField(null));                return sql;            case JUDGE_NULL:                sql = String.format(template.getTemplate(),                        cond.getColunm());                break;            case CLOUM_IN_FILED:                sql = String.format(template.getTemplate(),                        cond.getColunm(), cond.getField());                break;            default:                throw new RuntimeException("不支持的查询条件");        }        return sql;    }}