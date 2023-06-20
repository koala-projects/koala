package cn.koala.security.introspection.support;

import cn.koala.security.userdetails.CacheableUserDetailsService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * 基于用户认证的权限提取器
 *
 * @author Houtaroy
 */
public class UserAuthenticationAuthorityExtractor extends AbstractAuthorityExtractor {

  private final CacheableUserDetailsService userDetailsService;

  public UserAuthenticationAuthorityExtractor(CacheableUserDetailsService userDetailsService) {
    super(AuthorizationGrantType.AUTHORIZATION_CODE, AuthorizationGrantType.PASSWORD);
    this.userDetailsService = userDetailsService;
  }

  @Override
  public Collection<GrantedAuthority> extract(OAuth2AuthenticatedPrincipal principal) {
    Set<GrantedAuthority> result = new HashSet<>();
    Optional.ofNullable(this.userDetailsService.loadUserByUsername(principal.getName(), true))
      .map(UserDetails::getAuthorities)
      .ifPresent(result::addAll);
    return result;
  }
}
