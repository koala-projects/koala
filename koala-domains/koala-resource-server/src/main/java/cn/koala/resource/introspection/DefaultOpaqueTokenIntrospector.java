package cn.koala.resource.introspection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.DefaultOAuth2AuthenticatedPrincipal;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.security.oauth2.server.resource.introspection.NimbusOpaqueTokenIntrospector;
import org.springframework.security.oauth2.server.resource.introspection.OpaqueTokenIntrospector;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 自定义不透明令牌内省器
 *
 * @author Houtaroy
 */
public class DefaultOpaqueTokenIntrospector implements OpaqueTokenIntrospector {

  private final OpaqueTokenIntrospector delegate;

  private final List<AuthorityExtractor> authorityExtractors;

  public DefaultOpaqueTokenIntrospector(String introspectionUri, String clientId, String clientSecret,
                                        List<AuthorityExtractor> authorityExtractors) {
    this.delegate = new NimbusOpaqueTokenIntrospector(introspectionUri, clientId, clientSecret);
    this.authorityExtractors = authorityExtractors;
  }

  public OAuth2AuthenticatedPrincipal introspect(String token) {
    OAuth2AuthenticatedPrincipal principal = this.delegate.introspect(token);
    return new DefaultOAuth2AuthenticatedPrincipal(
      principal.getName(),
      principal.getAttributes(),
      this.extractAuthorities(principal)
    );
  }

  private Collection<GrantedAuthority> extractAuthorities(OAuth2AuthenticatedPrincipal principal) {
    Set<GrantedAuthority> result = new HashSet<>();
    for (AuthorityExtractor extractor : this.authorityExtractors) {
      if (extractor.support(principal)) {
        result.addAll(extractor.extract(principal));
        break;
      }
    }
    return result;
  }
}
