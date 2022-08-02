package cn.koala.oauth2;

import com.google.common.collect.Maps;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.OAuth2ErrorCodes;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.web.authentication.AuthenticationConverter;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * OAuth2密码模式授权转换器
 *
 * @author Houtaroy
 */
@SuppressWarnings("PMD")
public class OAuth2PasswordAuthenticationConverter implements AuthenticationConverter {

  @Override
  public Authentication convert(HttpServletRequest request) {
    if (isNotSupport(request.getParameter(OAuth2ParameterNames.GRANT_TYPE))) {
      return null;
    }

    MultiValueMap<String, String> parameters = OAuth2EndpointUtils.getParameters(request);
    String username = getParameterElseThrowException(parameters, OAuth2ParameterNames.USERNAME);
    String password = getParameterElseThrowException(parameters, OAuth2ParameterNames.PASSWORD);
    Authentication clientPrincipal = SecurityContextHolder.getContext().getAuthentication();
    if (clientPrincipal == null) {
      OAuth2EndpointUtils.parameterError(OAuth2ErrorCodes.INVALID_REQUEST, OAuth2ParameterNames.USERNAME);
    }

    Map<String, Object> additionalParameters = Maps.newHashMap();
    parameters.forEach((key, value) -> {
      if (isAdditionalParameters(key)) {
        additionalParameters.put(key, value.get(0));
      }
    });

    return new OAuth2PasswordAuthenticationToken(clientPrincipal, username, password, additionalParameters);
  }

  /**
   * 是否不支持
   *
   * @param grantType 授权类型
   * @return 判断结果
   */
  protected boolean isNotSupport(String grantType) {
    return !AuthorizationGrantType.PASSWORD.getValue().equals(grantType);
  }

  /**
   * 获取参数或抛出异常
   *
   * @param parameters    全部参数
   * @param parameterName 参数名称
   * @return 参数
   */
  protected String getParameterElseThrowException(MultiValueMap<String, String> parameters, String parameterName) {
    String result = parameters.getFirst(parameterName);
    if (!StringUtils.hasText(result) || parameters.get(parameterName).size() != 1) {
      OAuth2EndpointUtils.parameterError(OAuth2ErrorCodes.INVALID_REQUEST, OAuth2ParameterNames.PASSWORD);
    }
    return result;
  }

  /**
   * 是否为附加参数
   *
   * @param key 参数名称
   * @return 判断结果
   */
  protected boolean isAdditionalParameters(String key) {
    return !key.equals(OAuth2ParameterNames.USERNAME) && !key.equals(OAuth2ParameterNames.PASSWORD);
  }
}
