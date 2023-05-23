package cn.koala.security.builder.processor.support;

import org.springframework.core.annotation.Order;

/**
 * 登录页面权限校验放开处理器
 *
 * @author Houtaroy
 */
@Order(1000)
public class LoginPermitAllProcessor extends AbstractPermitAllProcessor {

  public LoginPermitAllProcessor() {
    super("/login/**");
  }
}
