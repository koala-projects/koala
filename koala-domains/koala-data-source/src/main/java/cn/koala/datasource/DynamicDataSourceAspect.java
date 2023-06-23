package cn.koala.datasource;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.util.StringUtils;

/**
 * 动态数据源切面
 *
 * @author Houtaroy
 */
@Aspect
public class DynamicDataSourceAspect {

  private static final ThreadLocal<String> DEFAULT_KEY = new ThreadLocal<>();

  @Before(value = "@annotation(key)")
  public void before(DynamicDataSourceKey key) {
    DEFAULT_KEY.set(DynamicDataSource.CONTEXT_HOLDER.get());
    if (StringUtils.hasText(key.value())) {
      DynamicDataSource.CONTEXT_HOLDER.set(key.value());
    }
  }

  @After(value = "@annotation(key)")
  public void after(DynamicDataSourceKey key) {
    DynamicDataSource.CONTEXT_HOLDER.set(DEFAULT_KEY.get());
    DEFAULT_KEY.remove();
  }
}
