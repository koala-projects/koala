package cn.koala.security;

import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer;

/**
 * Jwt上下文定制器
 *
 * @author Houtaroy
 */
public interface JwtEncodingContextCustomizer extends OAuth2TokenCustomizer<JwtEncodingContext> {
}
