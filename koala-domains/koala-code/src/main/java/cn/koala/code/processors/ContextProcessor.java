package cn.koala.code.processors;

import java.util.Map;

/**
 * 上下文加工器
 *
 * @author Houtaroy
 */
public interface ContextProcessor {
  Map<String, Object> process(Object context);

  default String getName() {
    return this.getClass().getName();
  }
}
