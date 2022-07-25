package cn.koala.oauth2;

import lombok.RequiredArgsConstructor;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;

/**
 * OAuth2资源服务配置定制器
 *
 * @author Houtaroy
 */
@RequiredArgsConstructor
@SuppressWarnings("PMD")
public class OAuth2ResourceServerConfigurerCustomizer
  implements Customizer<OAuth2ResourceServerConfigurer<HttpSecurity>> {

  protected final JwtDecoder jwtDecoder;

  @Override
  public void customize(OAuth2ResourceServerConfigurer<HttpSecurity> configurer) {
    configurer.jwt(jwt -> jwt.decoder(jwtDecoder).jwtAuthenticationConverter(converter()));
  }

  /**
   * 生成Jwt授权转换器
   *
   * @return Jwt授权转换器
   */
  protected JwtAuthenticationConverter converter() {
    JwtAuthenticationConverter result = new JwtAuthenticationConverter();
    JwtGrantedAuthoritiesConverter grantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
    grantedAuthoritiesConverter.setAuthorityPrefix("");
    result.setJwtGrantedAuthoritiesConverter(grantedAuthoritiesConverter);
    return result;
  }
}
