package cn.koala.codegen.name;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.Map;

/**
 * 静态复数转换器
 *
 * @author Houtaroy
 */
@RequiredArgsConstructor
public class StaticPluralConverter implements PluralConverter {

  private final Map<String, String> statics;

  @Override
  public String convert(@NonNull String source) {
    return statics.get(source);
  }

  @Override
  public boolean matches(String source) {
    return statics.containsKey(source);
  }
}
