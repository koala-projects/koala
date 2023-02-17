package cn.koala.security;

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
public abstract class SecurityHelper {
  public static UserDetails getCurrentUserDetails() {
    return Optional.ofNullable(SecurityContextHolder.getContext())
      .map(SecurityContext::getAuthentication)
      .map(Authentication::getPrincipal)
      .filter(principal -> principal instanceof OAuth2AuthenticatedPrincipal)
      .map(principal -> (OAuth2AuthenticatedPrincipal) principal)
      .map(SecurityHelper::principal2UserDetails)
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

  /**
   * 获取当前操作用户ID, 如果不存在则为null
   *
   * @return 当前操作用户ID
   */
  public static Long getCurrentUserId() {
    return Optional.ofNullable(getCurrentUserDetails())
      .filter(principal -> principal instanceof UserDetailsImpl)
      .map(principal -> ((UserDetailsImpl) principal).getId())
      .orElse(null);
  }
}
