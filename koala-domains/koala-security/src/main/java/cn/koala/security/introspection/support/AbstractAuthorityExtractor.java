package cn.koala.security.introspection.support;

import cn.koala.security.introspection.AuthorityExtractor;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;

import java.util.ArrayList;
import java.util.List;

/**
 * 抽象权限提取器
 * <p>
 * 默认实现了根据Token中的授权类型判断是否匹配
 *
 * @author Houtaroy
 */
public abstract class AbstractAuthorityExtractor implements AuthorityExtractor {

  private final List<String> grantTypes;

  public AbstractAuthorityExtractor(AuthorizationGrantType... grantTypes) {
    this.grantTypes = new ArrayList<>(grantTypes.length);
    for (AuthorizationGrantType grantType : grantTypes) {
      this.grantTypes.add(grantType.getValue());
    }
  }

  @Override
  public boolean support(OAuth2AuthenticatedPrincipal principal) {
    return this.grantTypes.contains(principal.<String>getAttribute(OAuth2ParameterNames.GRANT_TYPE));
  }
}
