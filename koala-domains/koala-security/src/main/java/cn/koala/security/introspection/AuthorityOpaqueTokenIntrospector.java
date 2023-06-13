package cn.koala.security.introspection;

import cn.koala.security.userdetails.CacheableUserDetailsService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.DefaultOAuth2AuthenticatedPrincipal;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.security.oauth2.server.resource.introspection.NimbusOpaqueTokenIntrospector;
import org.springframework.security.oauth2.server.resource.introspection.OpaqueTokenIntrospector;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * 自定义不透明令牌内省器
 *
 * @author Houtaroy
 */
public class AuthorityOpaqueTokenIntrospector implements OpaqueTokenIntrospector {

  protected final OpaqueTokenIntrospector delegate;
  protected final CacheableUserDetailsService userDetailsService;

  public AuthorityOpaqueTokenIntrospector(String introspectionUri, String clientId, String clientSecret, CacheableUserDetailsService userDetailsService) {
    this.delegate = new NimbusOpaqueTokenIntrospector(introspectionUri, clientId, clientSecret);
    this.userDetailsService = userDetailsService;
  }

  public OAuth2AuthenticatedPrincipal introspect(String token) {
    OAuth2AuthenticatedPrincipal principal = this.delegate.introspect(token);
    return new DefaultOAuth2AuthenticatedPrincipal(principal.getName(), principal.getAttributes(), getAuthorities(principal));
  }

  private Collection<GrantedAuthority> getAuthorities(OAuth2AuthenticatedPrincipal principal) {
    Set<GrantedAuthority> result = new HashSet<>();
    Optional.ofNullable(this.userDetailsService.loadUserByUsername(principal.getName(), true))
      .map(UserDetails::getAuthorities)
      .ifPresent(result::addAll);
    return result;
  }
}
