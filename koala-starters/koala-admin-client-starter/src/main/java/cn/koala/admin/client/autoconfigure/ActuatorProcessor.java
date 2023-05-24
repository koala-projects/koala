package cn.koala.admin.client.autoconfigure;

import cn.koala.security.builder.processor.support.AbstractPermitAllProcessor;
import org.springframework.core.annotation.Order;

/**
 * Actuator端点权限放开处理器
 *
 * @author Houtaroy
 */
@Order(2100)
public class ActuatorProcessor extends AbstractPermitAllProcessor {

  public ActuatorProcessor() {
    super("/actuator/**");
  }
}
