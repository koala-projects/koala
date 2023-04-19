DROP TABLE IF EXISTS wechat_mini_app_user;
CREATE TABLE wechat_mini_app_user
(
  `id`          BIGINT       NOT NULL AUTO_INCREMENT COMMENT '主键',
  `openid`      VARCHAR(100) NOT NULL COMMENT '用户唯一标识',
  `unionid`     VARCHAR(100) COMMENT '用户在开放平台的唯一标识符',
  `session_key` VARCHAR(100) NOT NULL COMMENT '会话密钥',
  `user_id`     INT          NOT NULL COMMENT '系统用户id',
  PRIMARY KEY (id)
) COMMENT = '微信小程序用户表';
