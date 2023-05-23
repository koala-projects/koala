package cn.koala.security.builder.support;

import cn.koala.security.builder.AuthorizationServerBuilder;
import cn.koala.security.builder.processor.AuthorizationServerProcessor;

import java.util.List;

/**
 * 默认认证服务安全构建器
 *
 * @author Houtaroy
 */
public class DefaultAuthorizationServerBuilder extends AbstractSecurityBuilder implements AuthorizationServerBuilder {

  public DefaultAuthorizationServerBuilder(List<AuthorizationServerProcessor> processors) {
    super(processors);
  }
}
