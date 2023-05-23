package cn.koala.security.builder.processor.support;

import cn.koala.security.SecurityProperties;
import org.springframework.core.annotation.Order;

/**
 * 自定义权限校验放开处理器
 *
 * @author Houtaroy
 */
@Order(3000)
public class CustomPermitAllProcessor extends AbstractPermitAllProcessor {

  public CustomPermitAllProcessor(SecurityProperties properties) {
    super(properties.getPermitAllPatterns().toArray(new String[0]));
  }
}
