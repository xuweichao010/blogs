/**
 匿名访问记录记录
 */
CREATE TABLE IF NOT EXISTS t_access (
  `id`          BIGINT    AUTO_INCREMENT COMMENT '记录ID',
  `ip`          VARCHAR(32)  NOT NULL COMMENT 'IP地址',
  `province`    VARCHAR(128) NOT NULL COMMENT '省份',
  `city`        VARCHAR(64)  NOT NULL COMMENT '城市',
  `city_code`   VARCHAR(128) NOT NULL COMMENT '百度城市编码',
  `access_time`  TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE = InnoDB COMMENT = '用户管理';




