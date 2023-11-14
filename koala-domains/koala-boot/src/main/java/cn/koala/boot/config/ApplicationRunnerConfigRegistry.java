package cn.koala.boot.config;

/**
 * ApplicationRunner配置注册表
 *
 * @author Houtaroy
 */
public interface ApplicationRunnerConfigRegistry {

  boolean getConfig(String name);

  boolean getConfigOrDefault(String name, boolean defaultValue);
}
