package cn.koala.security.persist;

import cn.koala.security.support.SecurityHelper;
import cn.koala.security.userdetails.KoalaUser;
import lombok.NonNull;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

/**
 * AuditorAware在Spring Security中的实现
 *
 * @author Houtaroy
 */
public class SecurityAuditorAware implements AuditorAware<Long> {

  @Override
  @NonNull
  public Optional<Long> getCurrentAuditor() {
    return Optional.ofNullable(SecurityHelper.getKoalaUser()).map(KoalaUser::getId);
  }
}
