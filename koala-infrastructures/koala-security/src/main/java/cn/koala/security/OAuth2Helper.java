package cn.koala.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.OAuth2ErrorCodes;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2ClientAuthenticationToken;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 * @author Houtaroy
 */
@SuppressWarnings("PMD")
public final class OAuth2Helper {
  private static final String REQUIRED_PARAMETER_TEMPLATE = "参数[%s]不能为空";
  public static final String ERROR_URI = "https://datatracker.ietf.org/doc/html/rfc6749#section-5.2";

  private OAuth2Helper() {
  }

  public static OAuth2ClientAuthenticationToken getClientPrincipalOrThrowException() {
    return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication())
      .map(OAuth2ClientAuthenticationToken.class::cast)
      .orElseThrow(() ->
        new OAuth2AuthenticationException(
          new OAuth2Error(OAuth2ErrorCodes.INVALID_REQUEST, "客户端信息不正确", ERROR_URI)
        )
      );
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
  private static OAuth2AuthenticationException exception(String parameterName) {
    return new OAuth2AuthenticationException(
      new OAuth2Error(
        OAuth2ErrorCodes.INVALID_REQUEST,
        String.format(REQUIRED_PARAMETER_TEMPLATE, parameterName),
        ERROR_URI
      )
    );
  }
}
