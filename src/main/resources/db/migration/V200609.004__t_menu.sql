/**
 菜单管理
 */
CREATE TABLE IF NOT EXISTS `t_menu`
(
    `id`        INT NOT NULL AUTO_INCREMENT COMMENT '菜单唯一标识',
    `name`      VARCHAR(32) COMMENT '名称',
    `code`      VARCHAR(32) COMMENT '按钮编号',
    `type`      INT COMMENT '类型 1:菜单  2:按钮',
    `parent_id` BIGINT COMMENT ' 父节点ID ',
    `sequence`  INT(11) COMMENT '排序编号',
    PRIMARY KEY (id)
) ENGINE = InnoDB COMMENT = '菜单管理';




