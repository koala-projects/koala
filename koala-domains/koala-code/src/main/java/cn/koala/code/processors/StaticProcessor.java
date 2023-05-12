package cn.koala.code.processors;

import java.util.Map;

/**
 * 简易上下文加工器
 *
 * @author Houtaroy
 */
public record StaticProcessor(String name, Object data) implements ContextProcessor {
  @Override
  public Map<String, Object> process(Object context) {
    return Map.of(name, data);
  }
}
