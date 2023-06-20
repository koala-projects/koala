package cn.koala.security.introspection.support;

import cn.koala.security.introspection.AuthorityExtractor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 组合权限提取器
 *
 * @author Houtaroy
 */
public class CompositeAuthorityExtractor implements AuthorityExtractor {

  private final List<AuthorityExtractor> extractors;

  public CompositeAuthorityExtractor(List<AuthorityExtractor> extractors) {
    this.extractors = extractors;
  }

  @Override
  public Collection<GrantedAuthority> extract(OAuth2AuthenticatedPrincipal principal) {
    Set<GrantedAuthority> result = new HashSet<>();
    for (AuthorityExtractor extractor : extractors) {
      if (extractor.support(principal)) {
        result.addAll(extractor.extract(principal));
        break;
      }
    }
    return result;
  }

  @Override
  public boolean support(OAuth2AuthenticatedPrincipal principal) {
    return true;
  }
}
