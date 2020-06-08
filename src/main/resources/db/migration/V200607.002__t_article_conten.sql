/**
  文章内容管理
 */
CREATE TABLE IF NOT EXISTS t_article_conten (
  `id`          BIGINT(32) NOT NULL UNIQUE COMMENT 'ID等于文章描述ID',
  `conten`      MEDIUMTEXT NOT NULL COMMENT  '文章内容',
  PRIMARY KEY (`id`)
) ENGINE = InnoDB COMMENT = '文章内容';




