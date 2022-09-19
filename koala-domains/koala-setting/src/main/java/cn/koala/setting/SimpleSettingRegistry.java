package cn.koala.setting;

import cn.koala.datamodel.Metadata;
import lombok.RequiredArgsConstructor;

import java.util.Map;

/**
 * 简易设置注册器, 使用设置服务进行注册
 *
 * @author Houtaroy
 */
@RequiredArgsConstructor
public class SimpleSettingRegistry implements SettingRegistry {
  protected final SettingService settingService;

  @Override
  public void registerSetting(Metadata settingDefinition, Map<String, Object> defaults) {
    settingService.add(settingDefinition, defaults);
  }
}
