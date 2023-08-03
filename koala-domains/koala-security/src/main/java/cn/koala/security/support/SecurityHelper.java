package cn.koala.security.support;

import cn.koala.security.userdetails.KoalaUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;

import java.util.Objects;
import java.util.Optional;

/**
 * 安全帮助类
 *
 * @author Houtaroy
 */
public abstract class SecurityHelper {

  public static KoalaUser getKoalaUser() {
    return Optional.ofNullable(SecurityContextHolder.getContext())
      .map(SecurityContext::getAuthentication)
      .map(Authentication::getPrincipal)
      .filter(principal -> principal instanceof OAuth2AuthenticatedPrincipal)
      .map(principal -> (OAuth2AuthenticatedPrincipal) principal)
      .map(SecurityHelper::principal2KoalaUser)
      .orElse(null);
  }

  private static KoalaUser principal2KoalaUser(OAuth2AuthenticatedPrincipal principal) {
    return KoalaUser.builder()
      .id(Long.valueOf(Objects.requireNonNull(principal.getAttribute("id"))))
      .username(principal.getAttribute("username"))
      .nickname(principal.getAttribute("nickname"))
      .permissionCodes(principal.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList())
      .build();
  }
}
