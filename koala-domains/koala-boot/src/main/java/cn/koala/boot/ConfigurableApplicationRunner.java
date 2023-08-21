package cn.koala.boot;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

/**
 * 可配置的ApplicationRunner接口
 *
 * @author Houtaroy
 */
public interface ConfigurableApplicationRunner extends ApplicationRunner {

  boolean isEnabled();

  @Override
  default void run(ApplicationArguments args) throws Exception {
    if (isEnabled()) {
      doRun(args);
    }
  }

  void doRun(ApplicationArguments args) throws Exception;
}
