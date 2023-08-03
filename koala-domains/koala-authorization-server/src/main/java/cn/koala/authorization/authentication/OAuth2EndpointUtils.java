/*
 * Copyright 2020-2022 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.koala.authorization.authentication;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.OAuth2ErrorCodes;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.oauth2.core.endpoint.PkceParameterNames;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Utility methods for the OAuth 2.0 Protocol Endpoints.
 *
 * @author Joe Grandja
 * @since 0.1.2
 */
public final class OAuth2EndpointUtils {
  public static final String ACCESS_TOKEN_REQUEST_ERROR_URI = "https://datatracker.ietf.org/doc/html/rfc6749#section-5.2";

  private OAuth2EndpointUtils() {
  }

  public static MultiValueMap<String, String> getParameters(HttpServletRequest request) {
    Map<String, String[]> parameterMap = request.getParameterMap();
    MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>(parameterMap.size());
    parameterMap.forEach((key, values) -> {
      for (String value : values) {
        parameters.add(key, value);
      }
    });
    return parameters;
  }

  static Map<String, Object> getParametersIfMatchesAuthorizationCodeGrantRequest(HttpServletRequest request, String... exclusions) {
    if (!matchesAuthorizationCodeGrantRequest(request)) {
      return Collections.emptyMap();
    }
    Map<String, Object> parameters = new HashMap<>(getParameters(request).toSingleValueMap());
    for (String exclusion : exclusions) {
      parameters.remove(exclusion);
    }
    return parameters;
  }

  static boolean matchesAuthorizationCodeGrantRequest(HttpServletRequest request) {
    return AuthorizationGrantType.AUTHORIZATION_CODE.getValue().equals(
      request.getParameter(OAuth2ParameterNames.GRANT_TYPE)) &&
      request.getParameter(OAuth2ParameterNames.CODE) != null;
  }

  static boolean matchesPkceTokenRequest(HttpServletRequest request) {
    return matchesAuthorizationCodeGrantRequest(request) &&
      request.getParameter(PkceParameterNames.CODE_VERIFIER) != null;
  }

  public static void throwError(String errorCode, String parameterName, String errorUri) {
    OAuth2Error error = new OAuth2Error(errorCode, "OAuth 2.0 Parameter: " + parameterName, errorUri);
    throw new OAuth2AuthenticationException(error);
  }

  public static Authentication getClientPrincipalElseThrowInvalidClient() {
    Authentication clientPrincipal = SecurityContextHolder.getContext().getAuthentication();
    if (clientPrincipal == null) {
      OAuth2EndpointUtils.throwError(
        OAuth2ErrorCodes.INVALID_REQUEST,
        OAuth2ErrorCodes.INVALID_CLIENT,
        OAuth2EndpointUtils.ACCESS_TOKEN_REQUEST_ERROR_URI);
    }
    return clientPrincipal;
  }
}
