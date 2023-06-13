package cn.koala.security.authentication.builder.support;

import cn.koala.security.authentication.builder.ResourceServerBuilder;
import cn.koala.security.authentication.builder.processor.ResourceServerProcessor;

import java.util.List;

/**
 * 默认资源服务安全构建器
 *
 * @author Houtaroy
 */
public class DefaultResourceServerBuilder extends AbstractSecurityBuilder implements ResourceServerBuilder {

  public DefaultResourceServerBuilder(List<ResourceServerProcessor> processors) {
    super(processors);
  }
}
