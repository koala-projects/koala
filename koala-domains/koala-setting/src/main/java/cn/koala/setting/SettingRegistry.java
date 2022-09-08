package cn.koala.setting;

import cn.koala.datamodel.PersistentMetadata;

import java.util.Map;

/**
 * 设置注册器
 *
 * @author Houtaroy
 */
public interface SettingRegistry {
  /**
   * 注册设置
   *
   * @param settingDefinition 设置定义
   * @param defaults          默认值
   */
  void registerSetting(PersistentMetadata settingDefinition, Map<String, Object> defaults);
}
