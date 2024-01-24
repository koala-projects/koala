package cn.koala.system.setting;

import cn.koala.exception.BusinessException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * JSON设置值解析策略
 *
 * @author Houtaroy
 */
@Slf4j

@RequiredArgsConstructor
public class JacksonSettingValueParseStrategy implements SettingValueParseStrategy<String> {

  @Getter
  private final SettingType settingType = SettingType.JSON;

  private final ObjectMapper objectMapper;

  @Override
  public String parse(String value) {
    try {
      return objectMapper.writeValueAsString(value);
    } catch (JsonProcessingException e) {
      LOGGER.error("JSON设置值解析异常", e);
      throw new BusinessException("设置值解析失败");
    }
  }
}
