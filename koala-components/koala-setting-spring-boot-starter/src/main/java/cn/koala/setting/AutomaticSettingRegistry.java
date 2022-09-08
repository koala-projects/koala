package cn.koala.setting;

import cn.koala.persistence.Codeable;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

import java.util.HashMap;
import java.util.List;

/**
 * 自动设置注册器
 *
 * @author Houtaroy
 */
@RequiredArgsConstructor
public class AutomaticSettingRegistry implements ApplicationRunner {

  protected final SettingDefinitionService settingDefinitionService;
  protected final SettingRegistry registry;
  protected final List<SettingRegistration> registrations;

  @Override
  public void run(ApplicationArguments args) {
    List<String> registeredCodes = settingDefinitionService.list(new HashMap<>(0))
      .stream().map(Codeable::getCode).toList();
    registrations.stream()
      .filter(registration -> !registeredCodes.contains(registration.getMetadata().getCode()))
      .forEach(registry::registerSetting);
  }
}
