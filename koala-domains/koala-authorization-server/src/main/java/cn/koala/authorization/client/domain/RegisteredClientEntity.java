package cn.koala.authorization.client.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.domain.Persistable;
import org.springframework.util.StringUtils;

import java.time.Instant;

/**
 * 注册客户端实体
 *
 * @author Houtaroy
 */
@Data
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class RegisteredClientEntity implements Persistable<String> {

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

  @JsonIgnore
  @Override
  public boolean isNew() {
    return !StringUtils.hasText(id);
  }
}
