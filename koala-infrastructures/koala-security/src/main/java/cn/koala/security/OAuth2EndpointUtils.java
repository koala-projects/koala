/*
 * Copyright 2020-2021 the original author or authors.
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
package cn.koala.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.OAuth2ErrorCodes;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2ClientAuthenticationToken;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Optional;

/**
 * OAuth2端点工具类
 *
 * @author Houtaroy
 */
@SuppressWarnings("PMD")
public final class OAuth2EndpointUtils {
  static final String ACCESS_TOKEN_REQUEST_ERROR_URI = "https://datatracker.ietf.org/doc/html/rfc6749#section-5.2";

  private OAuth2EndpointUtils() {
  }

  /**
   * 获取全部参数
   *
   * @param request 请求
   * @return 全部参数
   */
  public static MultiValueMap<String, String> getParameters(HttpServletRequest request) {
    Map<String, String[]> parameterMap = request.getParameterMap();
    MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>(parameterMap.size());
    parameterMap.forEach((key, values) -> {
      if (values.length > 0) {
        for (String value : values) {
          parameters.add(key, value);
        }
      }
    });
    return parameters;
  }

  /**
   * 参数错误
   *
   * @param errorCode     错误代码
   * @param parameterName 参数名称
   */
  public static void parameterError(String errorCode, String parameterName) {
    parameterError(errorCode, parameterName, ACCESS_TOKEN_REQUEST_ERROR_URI);
  }

  /**
   * 参数错误
   *
   * @param errorCode     错误代码
   * @param parameterName 参数名称
   * @param errorUri      错误信息地址
   */
  public static void parameterError(String errorCode, String parameterName, String errorUri) {
    OAuth2Error error = new OAuth2Error(errorCode, "OAuth 2.0 Parameter: " + parameterName, errorUri);
    throw new OAuth2AuthenticationException(error);
  }

  /**
   * 获取注册客户端或抛出异常
   *
   * @param token OAuth2客户端授权Token
   * @return 注册客户端
   */
  public static RegisteredClient getRegisteredClientElseThrowException(OAuth2ClientAuthenticationToken token) {
    return Optional.ofNullable(token).map(OAuth2ClientAuthenticationToken::getRegisteredClient)
      .orElseThrow(() -> new OAuth2AuthenticationException(OAuth2ErrorCodes.INVALID_CLIENT));
  }

  /**
   * 获取OAuth2客户端授权Token或抛出异常
   *
   * @param authentication 授权
   * @return OAuth2客户端授权Token
   */
  public static OAuth2ClientAuthenticationToken getOAuth2ClientAuthenticationTokenElseThrowException(
    Authentication authentication) {
    return Optional.ofNullable(authentication)
      .map(Authentication::getPrincipal)
      .filter(principal -> OAuth2ClientAuthenticationToken.class.isAssignableFrom(principal.getClass()))
      .map(OAuth2ClientAuthenticationToken.class::cast)
      .filter(OAuth2ClientAuthenticationToken::isAuthenticated)
      .orElseThrow(() -> new OAuth2AuthenticationException(OAuth2ErrorCodes.INVALID_CLIENT));
  }
}
