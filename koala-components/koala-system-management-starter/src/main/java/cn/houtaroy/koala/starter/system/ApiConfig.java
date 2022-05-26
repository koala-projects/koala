package cn.houtaroy.koala.starter.system;

import cn.koala.system.UserApi;
import cn.koala.system.UserApiImpl;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.OAuthFlow;
import io.swagger.v3.oas.models.security.OAuthFlows;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Houtaroy
 */
@Configuration
public class ApiConfig {

  /**
   * OpenApi配置的bean
   *
   * @return OpenApi
   */
  @Bean
  @SuppressWarnings("PMD")
  public OpenAPI openAPI() {
    return new OpenAPI().components(
      new Components().addSecuritySchemes(
        "OAuth2",
        new SecurityScheme().type(SecurityScheme.Type.OAUTH2).scheme("bearer").bearerFormat("JWT").name("Bearer")
          .flows(new OAuthFlows().password(new OAuthFlow().tokenUrl("/oauth2/token")))
      )
    );
  }

  /**
   * 用户接口的Bean
   *
   * @return 用户接口
   */
  @Bean
  public UserApi userApi() {
    return new UserApiImpl();
  }
}
