package cn.koala.database;

import cn.koala.persist.domain.Persistable;
import cn.koala.persist.domain.Stateful;

/**
 * 数据库接口
 *
 * @author Houtaroy
 */
@Deprecated
public interface Database extends Persistable<Long>, Stateful {
  String getName();

  String getUrl();

  String getUsername();

  String getPassword();

  String getCatalog();

  String getSchema();
}
