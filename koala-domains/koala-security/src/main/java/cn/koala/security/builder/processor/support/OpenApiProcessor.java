package cn.koala.security.builder.processor.support;

import org.springframework.core.annotation.Order;

/**
 * OpenApi权限放开处理器
 *
 * @author Houtaroy
 */
@Order(2000)
public class OpenApiProcessor extends AbstractPermitAllProcessor {

  public OpenApiProcessor() {
    super("/swagger*/**", "/v3/api-docs/**");
  }
}
