/**
  文章组管理
  每个用户都可以创建属于自己的组
 */
CREATE TABLE IF NOT EXISTS t_article_group
(
    `id`         BIGINT      NOT NULL UNIQUE COMMENT '组ID',
    `name`       VARCHAR(32) NOT NULL COMMENT '组名称',
    `account_id` BIGINT      NOT NULL COMMENT '账户ID',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB COMMENT = '文章组管理';




