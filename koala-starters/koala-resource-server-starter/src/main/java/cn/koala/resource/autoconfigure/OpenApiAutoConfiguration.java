package cn.koala.resource.autoconfigure;

import cn.koala.resource.builder.ResourceServerSecurityFilterChainPostProcessor;
import cn.koala.resource.builder.support.PermitAllPostProcessor;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.OAuthFlow;
import io.swagger.v3.oas.models.security.OAuthFlows;
import io.swagger.v3.oas.models.security.Scopes;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

/**
 * @author Houtaroy
 */
@Configuration
@ConditionalOnClass(name = "io.swagger.v3.oas.models.OpenAPI")
public class OpenApiAutoConfiguration {
  /**
   * OpenApi安全配置的bean
   *
   * @return OpenApi安全配置
   */
  @Bean
  @ConditionalOnMissingBean(name = "oauth2OpenApi")
  public OpenAPI oauth2OpenApi() {
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

  @Bean
  @Order(3300)
  @ConditionalOnMissingBean(name = "openApiPostProcessor")
  public ResourceServerSecurityFilterChainPostProcessor openApiPostProcessor() {
    return new PermitAllPostProcessor("/swagger*/**", "/v3/api-docs/**");
  }
}
