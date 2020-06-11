/**
 角色和菜单关系管理
 */
CREATE TABLE IF NOT EXISTS `t_role_menu`
(
    `role_id` BIGINT NOT NULL COMMENT '角色ID',
    `menu_id` BIGINT NOT NULL COMMENT '菜单ID',
    PRIMARY KEY (role_id, menu_id)
) ENGINE = InnoDB COMMENT = '角色和菜单管理';




