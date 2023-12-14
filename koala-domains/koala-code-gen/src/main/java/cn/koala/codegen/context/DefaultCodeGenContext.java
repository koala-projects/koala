package cn.koala.codegen.context;

import cn.koala.database.domain.DatabaseTable;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Houtaroy
 */
public class DefaultCodeGenContext implements CodeGenContext {

  private final Map<Object, Object> context;

  public DefaultCodeGenContext() {
    this(new HashMap<>());
  }

  public DefaultCodeGenContext(DatabaseTable table) {
    this();
    put("table", table);
  }

  public DefaultCodeGenContext(Map<Object, Object> context) {
    this.context = context;
  }

  @Override
  @SuppressWarnings("unchecked")
  public <T> T get(Object key) {
    return (T) context.get(key);
  }

  @Override
  public void put(Object key, Object value) {
    context.put(key, value);
  }

  @Override
  public Map<Object, Object> toMap() {
    return Collections.unmodifiableMap(context);
  }
}
