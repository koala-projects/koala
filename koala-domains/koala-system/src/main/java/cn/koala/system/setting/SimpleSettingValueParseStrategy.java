package cn.koala.system.setting;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.function.Function;

/**
 * 简易设置值解析策略
 *
 * @author Houtaroy
 */
@Getter
@RequiredArgsConstructor
public class SimpleSettingValueParseStrategy<T> implements SettingValueParseStrategy<T> {

  private final SettingType settingType;

  private final Function<String, T> parser;

  @Override
  public T parse(String value) {
    return parser.apply(value);
  }
}
