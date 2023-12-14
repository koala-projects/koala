package cn.koala.mapping;

import java.util.Map;

/**
 * 简易Mapping, 内置Map用于映射
 *
 * @author Houtaroy
 */
public class SimpleMapping<IN, OUT> implements Mapping<IN, OUT> {

  private final Map<IN, OUT> map;
  
  private final OUT defaultValue;

  public SimpleMapping(Map<IN, OUT> map) {
    this(map, null);
  }

  public SimpleMapping(Map<IN, OUT> map, OUT defaultValue) {
    this.map = map;
    this.defaultValue = defaultValue;
  }

  @Override
  public OUT map(IN input) {
    return map.getOrDefault(input, defaultValue);
  }
}
