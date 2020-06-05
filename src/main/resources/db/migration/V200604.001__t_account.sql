/**
账户管理
 */
CREATE TABLE IF NOT EXISTS t_account
(
    `id`          BIGINT(32) AUTO_INCREMENT COMMENT '用户ID',
    `account`     VARCHAR(32)  NOT NULL COMMENT '账号',
    `password`    VARCHAR(128) NOT NULL COMMENT '密码',
    `name`        VARCHAR(64)  NOT NULL COMMENT '用户名',
    `email`       VARCHAR(128) NOT NULL COMMENT '邮箱',
    `gender`      INT(11) COMMENT '性别 0-男性;1-女性',
    `create_time` DATETIME COMMENT '创建时间',
    `create_id`   VARCHAR(32) COMMENT '创建ID',
    `create_name` VARCHAR(32) COMMENT '创建名字',
    `update_time` DATETIME COMMENT '更新时间',
    `update_id`   VARCHAR(32) COMMENT '更新ID',
    `update_name` VARCHAR(32) DEFAULT NULL COMMENT '更新名字',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB COMMENT = '用户管理';




