package cn.koala.database;

import cn.koala.mybatis.IdModel;

/**
 * 数据库接口
 *
 * @author Houtaroy
 */
public interface Database extends IdModel<Long> {
  String getName();

  String getUrl();

  String getUsername();

  String getPassword();

  String getCatalog();

  String getSchema();
}
