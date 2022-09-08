package cn.koala.setting;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

import java.util.List;

/**
 * 自动设置注册器
 *
 * @author Houtaroy
 */
@RequiredArgsConstructor
public class AutomaticSettingRegistry implements ApplicationRunner {

  protected final SettingRegistry registry;
  protected final List<SettingRegistration> registrations;

  @Override
  public void run(ApplicationArguments args) {
    registrations.forEach(registry::registerSetting);
  }
}
