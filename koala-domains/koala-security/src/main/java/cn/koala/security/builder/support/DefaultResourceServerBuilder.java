package cn.koala.security.builder.support;

import cn.koala.security.builder.ResourceServerBuilder;
import cn.koala.security.builder.processor.ResourceServerProcessor;

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
