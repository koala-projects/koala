package cn.koala.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.web.authentication.AuthenticationConverter;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Houtaroy
 */
@SuppressWarnings("PMD")
public class OAuth2ResourceOwnerPasswordAuthenticationConverter implements AuthenticationConverter {
  @Override
  public OAuth2ResourceOwnerPasswordAuthenticationToken convert(HttpServletRequest request) {
    if (isNotSupport(request)) {
      return null;
    }
    OAuth2Helper.requiredParameter(request, OAuth2ParameterNames.CLIENT_ID);
    OAuth2Helper.requiredParameter(request, OAuth2ParameterNames.CLIENT_SECRET);
    return new OAuth2ResourceOwnerPasswordAuthenticationToken(
      AuthorizationGrantType.PASSWORD,
      OAuth2Helper.getClientPrincipalOrThrowException(),
      new UsernamePasswordAuthenticationToken(
        OAuth2Helper.getParameterOrThrowException(request, OAuth2ParameterNames.USERNAME),
        OAuth2Helper.getParameterOrThrowException(request, OAuth2ParameterNames.PASSWORD)
      ),
      null
    );
  }

  protected boolean isNotSupport(HttpServletRequest request) {
    return !AuthorizationGrantType.PASSWORD.getValue().equals(request.getParameter(OAuth2ParameterNames.GRANT_TYPE));
  }
}
