package cn.koala.security;

import cn.koala.security.entities.UserDetailsImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;

import java.util.Objects;
import java.util.Optional;

/**
 * 安全帮助类
 *
 * @author Houtaroy
 */
public abstract class SpringSecurityHelper {
  public static UserDetails getCurrentUserDetails() {
    return Optional.ofNullable(SecurityContextHolder.getContext())
      .map(SecurityContext::getAuthentication)
      .map(Authentication::getPrincipal)
      .filter(principal -> principal instanceof OAuth2AuthenticatedPrincipal)
      .map(principal -> (OAuth2AuthenticatedPrincipal) principal)
      .map(SpringSecurityHelper::principal2UserDetails)
      .orElse(null);
  }

  private static UserDetails principal2UserDetails(OAuth2AuthenticatedPrincipal principal) {
    return UserDetailsImpl.builder()
      .id(Long.valueOf(Objects.requireNonNull(principal.getAttribute("id"))))
      .username(principal.getAttribute("username"))
      .nickname(principal.getAttribute("nickname"))
      .permissionCodes(principal.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList())
      .build();
  }
}
