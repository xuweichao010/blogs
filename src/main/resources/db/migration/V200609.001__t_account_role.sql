/**
 账号角色映射
 */
CREATE TABLE IF NOT EXISTS t_account_role
(
    `account_id` BIGINT NOT NULL COMMENT '账户ID',
    `role_id`    BIGINT NOT NULL COMMENT '角色ID',
    PRIMARY KEY (`account_id`, `role_id`)
) ENGINE = InnoDB COMMENT = '用户管理';




