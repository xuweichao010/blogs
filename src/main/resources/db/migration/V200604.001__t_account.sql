/**
账户管理
 */
CREATE TABLE IF NOT EXISTS t_account
(
    `id`          BIGINT AUTO_INCREMENT COMMENT '用户ID',
    `account`     VARCHAR(32)  NOT NULL UNIQUE COMMENT '账号',
    `password`    VARCHAR(128) NOT NULL COMMENT '密码',
    `name`        VARCHAR(64)  NOT NULL COMMENT '用户名',
    `email`       VARCHAR(128) COMMENT '邮箱',
    `gender`      INT(11) COMMENT '性别 1-男性;2-女性',
    `state`       INT(1) COMMENT  '状态 1-正常;2-禁用',
    `create_time` DATETIME COMMENT '创建时间',
    `create_id`   VARCHAR(32) COMMENT '创建ID',
    `create_name` VARCHAR(32) COMMENT '创建名字',
    `update_time` DATETIME COMMENT '更新时间',
    `update_id`   VARCHAR(32) COMMENT '更新ID',
    `update_name` VARCHAR(32) DEFAULT NULL COMMENT '更新名字',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB COMMENT = '用户管理';




