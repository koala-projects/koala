package cn.koala.authorization.client.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.domain.Persistable;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;
import org.springframework.util.StringUtils;

import java.time.Instant;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 注册客户端数据传输实体
 *
 * @author Houtaroy
 */
@Data
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class RegisteredClientDTO implements Persistable<String> {

  private String id;

  private String clientId;

  private Instant clientIdIssuedAt;

  private String clientSecret;

  private Instant clientSecretExpiresAt;

  private String clientName;

  private Set<String> clientAuthenticationMethods;

  private Set<String> authorizationGrantTypes;

  private Set<String> redirectUris;

  private Set<String> scopes;

  private Map<String, Object> clientSettings;

  private Map<String, Object> tokenSettings;

  public RegisteredClient toRegisteredClient() {
    return RegisteredClient.withId(this.id)
      .clientId(this.clientId)
      .clientIdIssuedAt(this.clientIdIssuedAt)
      .clientSecret(this.clientSecret)
      .clientSecretExpiresAt(this.clientSecretExpiresAt)
      .clientName(this.clientName)
      .clientAuthenticationMethods(methods -> methods.addAll(this.clientAuthenticationMethods.stream().map(ClientAuthenticationMethod::new).collect(Collectors.toSet())))
      .authorizationGrantTypes(types -> types.addAll(this.authorizationGrantTypes.stream().map(AuthorizationGrantType::new).collect(Collectors.toSet())))
      .redirectUris(uris -> uris.addAll(this.redirectUris))
      .scopes(scopes -> scopes.addAll(this.scopes))
      .clientSettings(ClientSettings.withSettings(this.clientSettings).build())
      .tokenSettings(TokenSettings.withSettings(this.tokenSettings).build())
      .build();
  }

  public static RegisteredClientDTO from(RegisteredClient client) {
    return RegisteredClientDTO.builder()
      .id(client.getId())
      .clientIdIssuedAt(client.getClientIdIssuedAt())
      .clientSecret(client.getClientSecret())
      .clientSecretExpiresAt(client.getClientSecretExpiresAt())
      .clientName(client.getClientName())
      .clientAuthenticationMethods(client.getClientAuthenticationMethods().stream().map(ClientAuthenticationMethod::getValue).collect(Collectors.toSet()))
      .authorizationGrantTypes(client.getAuthorizationGrantTypes().stream().map(AuthorizationGrantType::getValue).collect(Collectors.toSet()))
      .redirectUris(client.getRedirectUris())
      .scopes(client.getScopes())
      .clientSettings(client.getClientSettings().getSettings())
      .tokenSettings(client.getTokenSettings().getSettings())
      .build();
  }

  @Override
  public boolean isNew() {
    return !StringUtils.hasText(id);
  }
}
