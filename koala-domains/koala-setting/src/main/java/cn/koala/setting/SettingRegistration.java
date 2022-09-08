package cn.koala.setting;

import cn.koala.datamodel.PersistentMetadata;

import java.util.Map;

/**
 * 设置注册记录
 *
 * @author Houtaroy
 */
public interface SettingRegistration {
  /**
   * 获取设置对应的元数据
   *
   * @return 元数据
   */
  PersistentMetadata getMetadata();

  /**
   * 获取设置默认值
   *
   * @return 设置默认值
   */
  Map<String, Object> getDefaults();
}
