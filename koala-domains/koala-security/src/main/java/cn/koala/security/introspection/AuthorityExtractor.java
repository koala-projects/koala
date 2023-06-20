package cn.koala.security.introspection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;

import java.util.Collection;

/**
 * 权限提取器
 *
 * @author Houtaroy
 */
public interface AuthorityExtractor {

  Collection<GrantedAuthority> extract(OAuth2AuthenticatedPrincipal principal);

  boolean support(OAuth2AuthenticatedPrincipal principal);
}
