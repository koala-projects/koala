package cn.koala.authorization.token;

import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext;

/**
 * Jwt OAuth2 Token 定制器接口
 * <p>
 * 用于标注 Jwt OAuth2 Token 定制器, 方便使用组合定制器
 *
 * @author Houtaroy
 */
public interface JwtOAuth2TokenCustomizer {
  void customize(JwtEncodingContext context);
}
