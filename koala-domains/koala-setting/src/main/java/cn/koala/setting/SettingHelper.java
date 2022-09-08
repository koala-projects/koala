package cn.koala.setting;

import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

/**
 * 设置帮助类
 *
 * @author Houtaroy
 */
public abstract class SettingHelper {

  /**
   * 解析设置项键值
   *
   * @param key 设置项键值
   * @return 解析结果
   */
  public static SettingKey parse(String key) {
    Assert.isTrue(
      StringUtils.countOccurrencesOf(key, SettingConstants.KEY_SEPARATOR) == 1,
      "非法的键值"
    );
    int separatorIndex = key.indexOf(SettingConstants.KEY_SEPARATOR);
    return new SettingKey(metadataCode(key.substring(0, separatorIndex)), key.substring(separatorIndex + 1));
  }

  /**
   * 处理设置的元数据代码, 如果不包含前缀则自动补充, 例如system -> _setting_system
   *
   * @param source 原始元数据代码
   * @return 补充后的元数据代码
   */
  public static String metadataCode(String source) {
    return source.startsWith(SettingConstants.CODE_PREFIX)
      ? source
      : "%s%s".formatted(SettingConstants.CODE_PREFIX, source);
  }
}
