package cn.koala.system.autoconfigure;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.OAuthFlow;
import io.swagger.v3.oas.models.security.OAuthFlows;
import io.swagger.v3.oas.models.security.Scopes;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;

/**
 * @author Houtaroy
 */
public class SwaggerAutoConfiguration {
  /**
   * OpenApi安全配置的bean
   *
   * @return OpenApi安全配置
   */
  @Bean
  public OpenAPI openAPI() {
    return new OpenAPI().components(
      new Components().addSecuritySchemes(
        "spring-security",
        new SecurityScheme().type(SecurityScheme.Type.OAUTH2).scheme("bearer").bearerFormat("JWT").name("Bearer")
          .flows(new OAuthFlows().authorizationCode(
            new OAuthFlow()
              .authorizationUrl("/oauth2/authorize")
              .tokenUrl("/oauth2/token")
              .scopes(new Scopes().addString("all", "全部"))
          ))
      )
    );
  }
}
