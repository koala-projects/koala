package cn.koala.boot.config;

import java.util.Map;

/**
 * 内存ApplicationRunner配置注册表实现类
 *
 * @author Houtaroy
 */
public class InMemoryApplicationRunnerConfigRegistry implements ApplicationRunnerConfigRegistry {

  private final Map<String, Boolean> configs;

  public InMemoryApplicationRunnerConfigRegistry(Map<String, Boolean> configs) {
    this.configs = configs;
  }

  @Override
  public boolean getConfig(String name) {
    return this.configs.get(name);
  }

  @Override
  public boolean getConfigOrDefault(String name, boolean defaultValue) {
    return this.configs.getOrDefault(name, defaultValue);
  }
}
