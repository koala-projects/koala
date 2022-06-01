package cn.koala.security;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.oauth2.server.authorization.JdbcOAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.OAuth2Authorization;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.util.StringUtils;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

/**
 * @author Houtaroy
 */
@SuppressWarnings("PMD")
public class KoalaJdbcOAuth2AuthorizationService extends JdbcOAuth2AuthorizationService {
  /**
   * 构造函数
   *
   * @param jdbcOperations             jdbc操作
   * @param registeredClientRepository 注册客户端存储库类
   */
  public KoalaJdbcOAuth2AuthorizationService(JdbcOperations jdbcOperations,
                                             RegisteredClientRepository registeredClientRepository) {
    super(jdbcOperations, registeredClientRepository);
  }

  @Override
  public void save(OAuth2Authorization authorization) {
    super.save(OAuth2Authorization.from(authorization).id(generateAuthorizationId(authorization)).build());
  }

  protected String generateAuthorizationId(OAuth2Authorization authorization) {
    try {
      byte[] values = MessageDigest.getInstance("MD5").digest(
        Map.of(
          OAuth2ParameterNames.USERNAME, authorization.getPrincipalName(),
          OAuth2ParameterNames.CLIENT_ID, authorization.getRegisteredClientId(),
          OAuth2ParameterNames.GRANT_TYPE, authorization.getAuthorizationGrantType().getValue(),
          OAuth2ParameterNames.SCOPE, StringUtils.collectionToCommaDelimitedString(
            authorization.getAttribute(OAuth2Authorization.AUTHORIZED_SCOPE_ATTRIBUTE_NAME)
          )
        ).toString().getBytes(StandardCharsets.UTF_8)
      );
      return String.format("%032x", new BigInteger(1, values));
    } catch (NoSuchAlgorithmException e) {
      throw new IllegalStateException("MD5 algorithm not available.  Fatal (should be in the JDK).");
    }
  }
}
