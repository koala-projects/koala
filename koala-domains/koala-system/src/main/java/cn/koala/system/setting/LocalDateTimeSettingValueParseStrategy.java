package cn.koala.system.setting;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.convert.Jsr310Converters;

import java.time.LocalDateTime;

/**
 * 日期时间设置值解析策略
 *
 * @author Houtaroy
 */
@Getter
@RequiredArgsConstructor
public class LocalDateTimeSettingValueParseStrategy implements SettingValueParseStrategy<LocalDateTime> {

  private final SettingType settingType = SettingType.DATETIME;

  @Override
  public LocalDateTime parse(String value) {
    return Jsr310Converters.StringToLocalDateTimeConverter.INSTANCE.convert(value);
  }
}
