package cn.koala.security;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.OAuthFlow;
import io.swagger.v3.oas.models.security.OAuthFlows;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;

/**
 * @author Houtaroy
 */
public class SwaggerConfig {
  /**
   * OpenApi安全配置的bean
   *
   * @return OpenApi安全配置
   */
  @Bean
  @SuppressWarnings("PMD")
  public OpenAPI openAPI() {
    return new OpenAPI().components(
      new Components().addSecuritySchemes(
        "spring-security",
        new SecurityScheme().type(SecurityScheme.Type.OAUTH2).scheme("bearer").bearerFormat("JWT").name("Bearer")
          .flows(new OAuthFlows().password(new OAuthFlow().tokenUrl("/oauth2/token")))
      )
    );
  }
}
