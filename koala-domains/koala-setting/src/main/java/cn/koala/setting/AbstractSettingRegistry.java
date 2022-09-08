package cn.koala.setting;

import cn.koala.datamodel.PersistentMetadata;

import java.util.Map;

/**
 * 抽象设置注册器, 使用设置服务进行注册
 *
 * @author Houtaroy
 */
public abstract class AbstractSettingRegistry implements SettingRegistry {

  protected final SettingService settingService;

  protected AbstractSettingRegistry(SettingService settingService) {
    this.settingService = settingService;
  }

  @Override
  public void registerSetting(PersistentMetadata settingDefinition, Map<String, Object> defaults) {
    settingService.add(settingDefinition, defaults);
  }
}
