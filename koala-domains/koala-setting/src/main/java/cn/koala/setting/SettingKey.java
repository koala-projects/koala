package cn.koala.setting;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 设置项键值, 包含元数据代码和属性代码
 *
 * @author Houtaroy
 */
@AllArgsConstructor
@Getter
public class SettingKey {
  protected String metadataCode;
  protected String propertyCode;
}
