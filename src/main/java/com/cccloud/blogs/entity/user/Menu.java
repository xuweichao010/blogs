package com.cccloud.blogs.entity.user;

import com.cccloud.blogs.easybatis.anno.table.Id;
import com.cccloud.blogs.easybatis.anno.table.Table;
import com.cccloud.blogs.entity.BaseEntity;
import lombok.Data;

import java.io.Serializable;

/**
 * 创建人：徐卫超
 * 创建时间：2019/5/5  11:44
 * 业务：
 * 功能：
 */
@Table("t_menu")
@Data
public class Menu extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 322299642679834516L;
    /**
     * 主键
     */
    @Id
    private Long id;
    /**
     * 名称
     */
    private String name;
    /**
     * 代码
     */
    private String code;
    /**
     * 类型;1:菜单  2:按钮
     */
    private Integer type;
    /**
     * 父节点ID
     */
    private Long parentId;
}
