package cn.koala.persist.domain;

import java.util.Optional;

/**
 * AuditorAware
 *
 * @author Houtaroy
 */
public interface AuditorAware<T> {
  Optional<T> getCurrentAuditor();
}
