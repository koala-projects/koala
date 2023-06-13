package cn.koala.security.persist;

import cn.koala.security.SpringSecurityHelper;
import cn.koala.security.userdetails.support.KoalaUser;
import lombok.NonNull;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

/**
 * AuditorAware在Spring Security中的实现
 *
 * @author Houtaroy
 */
public class SpringSecurityAuditorAware implements AuditorAware<Long> {
  @Override
  @NonNull
  public Optional<Long> getCurrentAuditor() {
    return Optional.ofNullable(SpringSecurityHelper.getCurrentUserDetails())
      .filter(principal -> principal instanceof KoalaUser)
      .map(KoalaUser.class::cast)
      .map(KoalaUser::getId);
  }
}
