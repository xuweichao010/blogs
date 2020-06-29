/**
  文章基本信息管理
 */
DROP TABLE  IF EXISTS `t_article`;
CREATE TABLE IF NOT EXISTS t_article
(
    `id`          BIGINT AUTO_INCREMENT COMMENT '文章ID',
    `group_id`    BIGINT  NOT NULL COMMENT '文章所属组',
    `account_id`  BIGINT NOT NULL COMMENT '创建人ID',
    `title`       VARCHAR(64)  NOT NULL COMMENT '文章标题',
    `describe`   VARCHAR(512) NOT NULL COMMENT '文章描述',
    `create_time` DATETIME COMMENT '创建时间',
    `update_time` DATETIME COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB COMMENT = '文章基本信息';




