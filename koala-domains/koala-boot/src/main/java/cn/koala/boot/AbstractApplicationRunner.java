package cn.koala.boot;

import lombok.Getter;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.Assert;

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

  public abstract String getName();

  @Override
  public boolean isEnabled() {
    Assert.notNull(this.configRegistry, "ApplicationRunner配置注册器不能为空");
    return this.configRegistry.getConfigOrDefault(getName(), false);
  }

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    this.configRegistry = applicationContext.getBean(ApplicationRunnerConfigRegistry.class);
  }
}
