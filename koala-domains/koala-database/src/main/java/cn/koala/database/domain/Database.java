package cn.koala.database.domain;

import cn.koala.data.domain.Auditable;
import cn.koala.data.domain.Enableable;
import cn.koala.data.domain.Sortable;
import cn.koala.data.domain.Systemic;

/**
 * 数据库接口
 *
 * @author Houtaroy
 */
public interface Database extends Auditable<Long, Long>, Sortable, Enableable, Systemic {

  String getName();

  String getUrl();

  String getUsername();

  String getPassword();

  String getCatalog();

  String getSchema();
}
