package cn.koala.setting;

import cn.koala.datamodel.Metadata;

import java.util.Map;
import java.util.Optional;

/**
 * 设置服务
 *
 * @author Houtaroy
 */
public interface SettingService {
  /**
   * 查看设置内容
   *
   * @param id 设置id
   * @return 设置内容
   */
  Optional<Map<String, Object>> load(String id);

  /**
   * 根据键值查看设置项内容, 键值格式为: ${设置名称}.${设置项属性名称}, 例如: system.default-password
   *
   * @param key 设置项键值
   * @return 设置项内容
   */
  Optional<Object> loadByKey(String key);

  /**
   * 新增设置
   *
   * @param settingDefinition 设置定义
   * @param defaults          默认值
   */
  void add(Metadata settingDefinition, Map<String, Object> defaults);

  /**
   * 更新设置内容
   *
   * @param id       设置id
   * @param settings 设置内容
   */
  void update(String id, Map<String, Object> settings);
}
