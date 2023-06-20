package cn.koala.security.introspection;

import org.springframework.security.oauth2.core.DefaultOAuth2AuthenticatedPrincipal;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.security.oauth2.server.resource.introspection.NimbusOpaqueTokenIntrospector;
import org.springframework.security.oauth2.server.resource.introspection.OpaqueTokenIntrospector;

/**
 * 自定义不透明令牌内省器
 *
 * @author Houtaroy
 */
public class AuthorityOpaqueTokenIntrospector implements OpaqueTokenIntrospector {

  protected final OpaqueTokenIntrospector delegate;
  private final AuthorityExtractor authorityExtractor;

  public AuthorityOpaqueTokenIntrospector(String introspectionUri, String clientId, String clientSecret, AuthorityExtractor authorityExtractor) {
    this.delegate = new NimbusOpaqueTokenIntrospector(introspectionUri, clientId, clientSecret);
    this.authorityExtractor = authorityExtractor;
  }

  public OAuth2AuthenticatedPrincipal introspect(String token) {
    OAuth2AuthenticatedPrincipal principal = this.delegate.introspect(token);
    return new DefaultOAuth2AuthenticatedPrincipal(
      principal.getName(),
      principal.getAttributes(),
      this.authorityExtractor.extract(principal)
    );
  }
}
