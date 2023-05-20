package cn.koala.demo;

import cn.koala.persist.initializer.support.AbstractModuleInitializer;
import org.springframework.stereotype.Component;

/**
 * 演示应用初始化器
 *
 * @author Houtaroy
 */
@Component
public class DemoInitializer extends AbstractModuleInitializer {
  @Override
  public String getName() {
    return "demo";
  }

  @Override
  public int getOrder() {
    return 5800;
  }
}
