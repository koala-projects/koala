package cn.koala.security;

import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

/**
 * OAuth2授权服务定制器
 *
 * @author Houtaroy
 */
public interface AuthorizationServerCustomizer extends Customizer<HttpSecurity> {
}
