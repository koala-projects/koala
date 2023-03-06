package cn.koala.code.processors;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Map;

/**
 * 简易上下文加工器
 *
 * @author Houtaroy
 */
@Data
@RequiredArgsConstructor
public class StaticProcessor implements ContextProcessor {
  protected final String name;
  protected final Object data;

  @Override
  public Map<String, Object> process(Object context) {
    return Map.of(name, data);
  }
}
