package cn.koala.code.processors;

import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

/**
 * 基础上下文加工抽象类
 * <p>
 * 增加上下文类型泛型, 实现{@link #doProcess(Object) doProcess}模板方法完成加工逻辑
 *
 * @author Houtaroy
 */
@RequiredArgsConstructor
public abstract class AbstractContextProcessor<T> implements ContextProcessor {

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
