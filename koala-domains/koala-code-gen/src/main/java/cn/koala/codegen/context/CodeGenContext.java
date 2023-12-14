package cn.koala.codegen.context;

import cn.koala.database.domain.DatabaseTable;

import java.util.Map;

/**
 * @author Houtaroy
 */
public interface CodeGenContext {

  default DatabaseTable getTable() {
    return get("table");
  }

  <T> T get(Object key);

  void put(Object key, Object value);

  Map<Object, Object> toMap();
}
