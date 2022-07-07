package cn.koala.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.OAuth2ErrorCodes;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2ClientAuthenticationToken;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * OAuth2帮助类
 *
 * @author Houtaroy
 */
@SuppressWarnings("PMD")
public final class OAuth2Helper {
  private static final String REQUIRED_PARAMETER_TEMPLATE = "参数[%s]不能为空";
  public static final String ERROR_URI = "https://datatracker.ietf.org/doc/html/rfc6749#section-5.2";

  private OAuth2Helper() {
    throw new IllegalStateException("工具类不允许实例化");
  }

  /**
   * 获取注册客户端或抛出无效客户端异常
   *
   * @param authentication 认证信息
   * @return 注册客户端
   */
  public static RegisteredClient getRegisteredClientElseThrowInvalidClient(Authentication authentication) {
    return getAuthenticatedClient(authentication)
      .map(OAuth2ClientAuthenticationToken::getRegisteredClient)
      .orElseThrow(() -> new OAuth2AuthenticationException(OAuth2ErrorCodes.INVALID_CLIENT));
  }

  /**
   * 获取认证客户端Token或抛出无效客户端异常
   *
   * @param authentication 认证信息
   * @return 认证客户端Token
   */
  public static OAuth2ClientAuthenticationToken getAuthenticatedClientElseThrowInvalidClient(
    Authentication authentication) {
    return getAuthenticatedClient(authentication)
      .orElseThrow(() -> new OAuth2AuthenticationException(OAuth2ErrorCodes.INVALID_CLIENT));
  }

  /**
   * 获取认证客户端Token
   *
   * @param authentication 认证信息
   * @return 认证客户端Token, Optional
   */
  public static Optional<OAuth2ClientAuthenticationToken> getAuthenticatedClient(Authentication authentication) {
    return Optional.ofNullable(authentication)
      .filter(data -> OAuth2ClientAuthenticationToken.class.isAssignableFrom(data.getClass()))
      .map(OAuth2ClientAuthenticationToken.class::cast)
      .filter(AbstractAuthenticationToken::isAuthenticated);
  }

  /**
   * 获取认证信息授权列表
   *
   * @param authentication 认证信息
   * @return 授权列表
   */
  public static Set<String> getAuthorities(Authentication authentication) {
    return authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toSet());
  }

  /**
   * 获取参数或抛出异常
   *
   * @param request       请求
   * @param parameterName 参数名称
   * @return 参数值
   */
  public static String getParameterOrThrowException(HttpServletRequest request, String parameterName) {
    return Optional.ofNullable(request.getParameter(parameterName))
      .filter(StringUtils::hasText)
      .orElseThrow(() -> exception(parameterName));
  }

  /**
   * 必须参数检查, 如果不存在则抛出异常
   *
   * @param request       请求
   * @param parameterName 参数名称
   */
  public static void requiredParameter(HttpServletRequest request, String parameterName) {
    if (!StringUtils.hasText(request.getParameter(parameterName))) {
      throw exception(parameterName);
    }
  }

  /**
   * 抛出认证异常
   *
   * @param parameterName 参数名称
   */
  public static OAuth2AuthenticationException exception(String parameterName) {
    return exception(OAuth2ErrorCodes.INVALID_REQUEST, String.format(REQUIRED_PARAMETER_TEMPLATE, parameterName));
  }

  /**
   * 生成OAuth2异常
   *
   * @param code        异常代码
   * @param description 异常描述
   * @return OAuth2异常
   */
  public static OAuth2AuthenticationException exception(String code, String description) {
    return new OAuth2AuthenticationException(
      new OAuth2Error(code, description, ERROR_URI)
    );
  }
}
