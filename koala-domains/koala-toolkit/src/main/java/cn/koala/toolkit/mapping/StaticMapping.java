package cn.koala.toolkit.mapping;

import java.util.HashMap;
import java.util.Map;

/**
 * 静态映射器
 *
 * @author Houtaroy
 */
@Deprecated
public class StaticMapping<S, T> implements Mapping<S, T> {

  protected final Map<S, T> statics;

  public StaticMapping(Map<S, T> statics) {
    this.statics = new HashMap<>(statics);
  }

  @Override
  public T map(S source) {
    return statics.get(source);
  }
}
