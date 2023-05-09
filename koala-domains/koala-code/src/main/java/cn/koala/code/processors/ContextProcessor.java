package cn.koala.code.processors;

import java.util.Map;

/**
 * 上下文加工器
 * <p>
 * 用于把上下文加工为模板引擎中需要的数据
 *
 * @author Houtaroy
 */
public interface ContextProcessor {

  Map<String, Object> process(Object context);

  /**
   * 加工器唯一标识, 默认为当前类名
   *
   * @return 加工器名称
   */
  default String getName() {
    return this.getClass().getName();
  }
}
