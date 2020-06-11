/**
 角色管理
 */
CREATE TABLE IF NOT EXISTS t_role
(
    `id`          BIGINT AUTO_INCREMENT COMMENT '角色ID',
    `name`        VARCHAR(64) NOT NULL COMMENT '用户名',
    `describe`    VARCHAR(128) COMMENT '描述',
    `create_time` DATETIME COMMENT '创建时间',
    `create_id`   VARCHAR(32) COMMENT '创建ID',
    `create_name` VARCHAR(32) COMMENT '创建名字',
    `update_time` DATETIME COMMENT '更新时间',
    `update_id`   VARCHAR(32) COMMENT '更新ID',
    `update_name` VARCHAR(32) DEFAULT NULL COMMENT '更新名字',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB COMMENT = '用户管理';




