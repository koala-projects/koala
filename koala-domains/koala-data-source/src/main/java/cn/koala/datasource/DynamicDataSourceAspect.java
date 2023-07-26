package cn.koala.datasource;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.util.StringUtils;

/**
 * 动态数据源切面
 *
 * @author Houtaroy
 */
@Aspect
@Order(3100)
public class DynamicDataSourceAspect {

  private static final ThreadLocal<String> ORIGINAL_KEY_HOLDER = new ThreadLocal<>();

  @Before(value = "@annotation(key)")
  public void before(DynamicDataSourceKey key) {
    ORIGINAL_KEY_HOLDER.set(DynamicDataSource.KEY_HOLDER.get());
    if (StringUtils.hasText(key.value())) {
      DynamicDataSource.KEY_HOLDER.set(key.value());
    }
  }

  @After(value = "@annotation(key)")
  public void after(DynamicDataSourceKey key) {
    DynamicDataSource.KEY_HOLDER.set(ORIGINAL_KEY_HOLDER.get());
    ORIGINAL_KEY_HOLDER.remove();
  }
}
