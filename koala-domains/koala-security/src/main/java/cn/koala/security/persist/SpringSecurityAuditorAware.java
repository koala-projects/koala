package cn.koala.security.persist;

import cn.koala.persist.domain.AuditorAware;
import cn.koala.security.SpringSecurityHelper;
import cn.koala.security.entities.UserDetailsImpl;

import java.util.Optional;

/**
 * AuditorAware在Spring Security中的实现
 *
 * @author Houtaroy
 */
public class SpringSecurityAuditorAware implements AuditorAware<Long> {
  @Override
  public Optional<Long> getCurrentAuditor() {
    return Optional.ofNullable(SpringSecurityHelper.getCurrentUserDetails())
      .filter(principal -> principal instanceof UserDetailsImpl)
      .map(principal -> ((UserDetailsImpl) principal).getId());
  }
}
