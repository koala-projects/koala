package cn.koala.codegen.context.type;

import com.google.common.base.CaseFormat;
import lombok.RequiredArgsConstructor;

import java.util.Map;

/**
 * @author Houtaroy
 */
@RequiredArgsConstructor
public abstract class AbstractJdbcTypeMapping<T extends Enum<?>> implements JdbcTypeMapping {

  private final Map<Integer, T> map;

  private final T defaultValue;

  private final CaseFormat format;

  @Override
  public String map(Integer input) {
    return CaseFormat.UPPER_UNDERSCORE.to(format, map.getOrDefault(input, defaultValue).name());
  }
}
