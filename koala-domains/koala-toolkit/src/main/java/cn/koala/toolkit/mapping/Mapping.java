package cn.koala.toolkit.mapping;

import org.springframework.lang.Nullable;

import java.util.Optional;

/**
 * 映射器接口
 *
 * @author Houtaroy
 */
@Deprecated
public interface Mapping<S, T> {

  @Nullable
  T map(S source);

  default T map(S source, T defaultValue) {
    return Optional.ofNullable(map(source))
      .orElse(defaultValue);
  }
}
