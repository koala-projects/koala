package cn.koala.security.authentication.token;

import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext;

/**
 * Jwt OAuth2 Token 自定义器接口
 * <p>
 * 用于标注Jwt OAuth2 Token自定义器, 方便使用组合自定义器
 *
 * @author Houtaroy
 */
public interface JwtOAuth2TokenCustomizer {
  void customize(JwtEncodingContext context);
}
