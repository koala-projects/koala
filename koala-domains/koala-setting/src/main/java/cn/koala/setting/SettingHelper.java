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
    return new SettingKey(key.substring(0, separatorIndex), key.substring(separatorIndex + 1));
  }
}
