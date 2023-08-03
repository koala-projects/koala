package cn.koala.authorization.authentication;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.lang.Nullable;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.OAuth2ErrorCodes;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.web.authentication.AuthenticationConverter;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;

import java.util.Map;
import java.util.stream.Collectors;

public class OAuth2ResourceOwnerPasswordAuthenticationConverter implements AuthenticationConverter {

  @Nullable
  @Override
  public Authentication convert(HttpServletRequest request) {

    // grant_type (REQUIRED)
    String grantType = request.getParameter(OAuth2ParameterNames.GRANT_TYPE);
    if (!AuthorizationGrantType.PASSWORD.getValue().equals(grantType)) {
      return null;
    }

    MultiValueMap<String, String> parameters = OAuth2EndpointUtils.getParameters(request);

    // scope (OPTIONAL)
    String scope = parameters.getFirst(OAuth2ParameterNames.SCOPE);
    if (StringUtils.hasText(scope) &&
      parameters.get(OAuth2ParameterNames.SCOPE).size() != 1) {
      OAuth2EndpointUtils.throwError(
        OAuth2ErrorCodes.INVALID_REQUEST,
        OAuth2ParameterNames.SCOPE,
        OAuth2EndpointUtils.ACCESS_TOKEN_REQUEST_ERROR_URI);
    }

    // username (REQUIRED)
    String username = parameters.getFirst(OAuth2ParameterNames.USERNAME);
    if (!StringUtils.hasText(username) ||
      parameters.get(OAuth2ParameterNames.USERNAME).size() != 1) {
      OAuth2EndpointUtils.throwError(
        OAuth2ErrorCodes.INVALID_REQUEST,
        OAuth2ParameterNames.USERNAME,
        OAuth2EndpointUtils.ACCESS_TOKEN_REQUEST_ERROR_URI);
    }

    // username (REQUIRED)
    String password = parameters.getFirst(OAuth2ParameterNames.PASSWORD);
    if (!StringUtils.hasText(username) ||
      parameters.get(OAuth2ParameterNames.PASSWORD).size() != 1) {
      OAuth2EndpointUtils.throwError(
        OAuth2ErrorCodes.INVALID_REQUEST,
        OAuth2ParameterNames.PASSWORD,
        OAuth2EndpointUtils.ACCESS_TOKEN_REQUEST_ERROR_URI);
    }

    Authentication clientPrincipal = OAuth2EndpointUtils.getClientPrincipalElseThrowInvalidClient();

    Map<String, Object> additionalParameters = parameters.entrySet().stream()
      .filter(e -> isAdditionalParameter(e.getKey()))
      .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().get(0)));

    return new OAuth2ResourceOwnerPasswordAuthenticationToken(username, password, clientPrincipal, additionalParameters);
  }

  protected boolean isAdditionalParameter(String key) {
    return !OAuth2ParameterNames.SCOPE.equals(key) &&
      !OAuth2ParameterNames.USERNAME.equals(key) &&
      !OAuth2ParameterNames.PASSWORD.equals(key);
  }

}
