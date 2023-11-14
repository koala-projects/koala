package cn.koala.boot;

import cn.koala.boot.config.ApplicationRunnerConfigRegistry;
import lombok.Getter;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * ApplicationRunner抽象类
 * <p>
 * 内置了{@link ApplicationRunnerConfigRegistry ApplicationRunnerConfigRegistry}配置注册表
 * <p>
 * 根据名称读取配置, 并判断是否启用
 *
 * @author Houtaroy
 */
@Getter
public abstract class AbstractApplicationRunner implements ConfigurableApplicationRunner, ApplicationContextAware {

  protected ApplicationRunnerConfigRegistry configRegistry;

  protected abstract String getName();

  protected abstract boolean getDefault();

  @Override
  public boolean isEnabled() {
    return this.configRegistry.getConfigOrDefault(getName(), getDefault());
  }

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    this.configRegistry = applicationContext.getBean(ApplicationRunnerConfigRegistry.class);
  }
}
