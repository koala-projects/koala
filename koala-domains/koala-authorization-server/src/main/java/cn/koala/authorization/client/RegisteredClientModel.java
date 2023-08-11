package cn.koala.authorization.client;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.Instant;

/**
 * TODO: 修改类描述
 *
 * @author Houtaroy
 */
@Data
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class RegisteredClientModel {

  private String id;

  private String clientId;

  private Instant clientIdIssuedAt;

  private String clientSecret;

  private Instant clientSecretExpiresAt;

  private String clientName;

  private String clientAuthenticationMethods;

  private String authorizationGrantTypes;

  private String redirectUris;

  private String scopes;

  private String clientSettings;

  private String tokenSettings;
}
