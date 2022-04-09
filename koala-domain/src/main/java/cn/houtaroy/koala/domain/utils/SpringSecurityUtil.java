package cn.houtaroy.koala.domain.utils;

import cn.houtaroy.koala.domain.models.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.introspection.OAuth2IntrospectionAuthenticatedPrincipal;

import java.util.Optional;

/**
 * @author Houtaroy
 */
public final class SpringSecurityUtil {

  private SpringSecurityUtil() {
    throw new IllegalStateException("工具类不允许实例化");
  }

  /**
   * 当前用户
   *
   * @return 用户
   */
  public static Optional<User> currentUser() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    Object result = authentication.getPrincipal() instanceof OAuth2IntrospectionAuthenticatedPrincipal principal
      ? principal.getAttribute("user_principal") : authentication.getPrincipal();
    return Optional.ofNullable(result).map(User.class::cast);
  }
}
