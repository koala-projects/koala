package cn.koala.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.DefaultOAuth2AuthenticatedPrincipal;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.security.oauth2.core.OAuth2TokenIntrospectionClaimNames;
import org.springframework.security.oauth2.server.resource.introspection.NimbusOpaqueTokenIntrospector;
import org.springframework.security.oauth2.server.resource.introspection.OpaqueTokenIntrospector;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 自定义不透明令牌内省器
 *
 * @author Houtaroy
 */
public class AuthoritiesOpaqueTokenIntrospector implements OpaqueTokenIntrospector {
  protected final OpaqueTokenIntrospector delegate;

  public AuthoritiesOpaqueTokenIntrospector(String introspectionUri, String clientId, String clientSecret) {
    this.delegate = new NimbusOpaqueTokenIntrospector(introspectionUri, clientId, clientSecret);
  }

  public OAuth2AuthenticatedPrincipal introspect(String token) {
    OAuth2AuthenticatedPrincipal principal = this.delegate.introspect(token);
    return new DefaultOAuth2AuthenticatedPrincipal(principal.getName(), principal.getAttributes(), extractAuthorities(principal));
  }

  private Collection<GrantedAuthority> extractAuthorities(OAuth2AuthenticatedPrincipal principal) {
    Set<GrantedAuthority> result = new HashSet<>();
    List<String> scopes = principal.getAttribute(OAuth2TokenIntrospectionClaimNames.SCOPE);
    if (!CollectionUtils.isEmpty(scopes)) {
      result.addAll(scopes.stream().map(SimpleGrantedAuthority::new).toList());
    }
    return result;
  }
}
