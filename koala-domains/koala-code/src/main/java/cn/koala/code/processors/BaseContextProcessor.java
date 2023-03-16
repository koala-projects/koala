package cn.koala.code.processors;

import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

/**
 * 基础上下文加工抽象类
 *
 * @author Houtaroy
 */
@RequiredArgsConstructor
public abstract class BaseContextProcessor<T> implements ContextProcessor {
  protected final Class<T> objectClass;

  @Override
  public Map<String, Object> process(Object context) {
    Map<String, Object> result = new HashMap<>();
    if (objectClass.isAssignableFrom(context.getClass())) {
      result.putAll(doProcess(objectClass.cast(context)));
    }
    return result;
  }

  protected abstract Map<String, Object> doProcess(T context);
}
