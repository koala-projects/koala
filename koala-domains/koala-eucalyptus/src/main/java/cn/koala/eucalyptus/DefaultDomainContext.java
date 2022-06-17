package cn.koala.eucalyptus;

import java.util.HashMap;
import java.util.Optional;

/**
 * 默认领域上下文, 基于HashMap
 *
 * @author Houtaroy
 */
public class DefaultDomainContext extends HashMap<String, Object> implements DomainContext {
  @Override
  public Domain getDomain() {
    return Optional.ofNullable(get("domain"))
      .filter(data -> data instanceof Domain)
      .map(Domain.class::cast)
      .orElse(null);
  }
}
