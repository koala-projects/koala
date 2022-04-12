package cn.houtaroy.koala.component.eucalyptus.domain;

import cn.houtaroy.koala.component.jdbc.Table;

/**
 * @author Houtaroy
 */
public interface Converter {

  /**
   * 转换为领域定义
   *
   * @param table 数据库表
   * @return 领域定义
   */
  Domain convert(Table table);
}
