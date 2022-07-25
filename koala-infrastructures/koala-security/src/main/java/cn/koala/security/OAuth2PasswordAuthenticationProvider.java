package cn.koala.security;

import com.google.common.collect.Maps;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClaimAccessor;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.OAuth2ErrorCodes;
import org.springframework.security.oauth2.core.OAuth2Token;
import org.springframework.security.oauth2.core.OAuth2TokenType;
import org.springframework.security.oauth2.server.authorization.OAuth2Authorization;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2AccessTokenAuthenticationToken;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2ClientAuthenticationToken;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.context.ProviderContextHolder;
import org.springframework.security.oauth2.server.authorization.token.DefaultOAuth2TokenContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenGenerator;

import java.security.Principal;
import java.util.stream.Collectors;

/**
 * OAuth2密码模式授权提供者
 *
 * @author Houtaroy
 */
@SuppressWarnings("PMD")
public class OAuth2PasswordAuthenticationProvider implements AuthenticationProvider {

  protected final AuthenticationManager authenticationManager;
  protected final OAuth2AuthorizationService authorizationService;
  protected final OAuth2TokenGenerator<? extends OAuth2Token> tokenGenerator;

  /**
   * OAuth2密码模式提供者构造器
   *
   * @param authenticationManager 授权管理器
   * @param authorizationService  OAuth2授权服务
   * @param tokenGenerator        令牌生成器
   */
  public OAuth2PasswordAuthenticationProvider(AuthenticationManager authenticationManager,
                                              OAuth2AuthorizationService authorizationService,
                                              OAuth2TokenGenerator<? extends OAuth2Token> tokenGenerator) {
    this.authenticationManager = authenticationManager;
    this.authorizationService = authorizationService;
    this.tokenGenerator = tokenGenerator;
  }

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    OAuth2PasswordAuthenticationToken passwordAuthenticationToken = (OAuth2PasswordAuthenticationToken) authentication;
    OAuth2ClientAuthenticationToken clientAuthenticationToken =
      OAuth2EndpointUtils.getOAuth2ClientAuthenticationTokenElseThrowException(passwordAuthenticationToken);
    RegisteredClient client = OAuth2EndpointUtils.getRegisteredClientElseThrowException(clientAuthenticationToken);
    if (!client.getAuthorizationGrantTypes().contains(AuthorizationGrantType.PASSWORD)) {
      throw new OAuth2AuthenticationException(new OAuth2Error(OAuth2ErrorCodes.UNAUTHORIZED_CLIENT));
    }

    Authentication userAuthentication = getUserAuthenticationElseThrowException(passwordAuthenticationToken);

    DefaultOAuth2TokenContext.Builder tokenContextBuilder = DefaultOAuth2TokenContext.builder()
      .authorizationGrantType(AuthorizationGrantType.PASSWORD)
      .registeredClient(client)
      .authorizedScopes(
        userAuthentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toSet())
      )
      .principal(userAuthentication)
      .providerContext(ProviderContextHolder.getProviderContext())
      .authorizationGrant(authentication);

    OAuth2Authorization.Builder authorizationBuilder = OAuth2Authorization.withRegisteredClient(client)
      .principalName(userAuthentication.getName()).authorizationGrantType(AuthorizationGrantType.PASSWORD);

    OAuth2AccessToken accessToken = accessToken(tokenContextBuilder, authorizationBuilder);

    authorizationBuilder.attribute(Principal.class.getName(), userAuthentication);
    this.authorizationService.save(authorizationBuilder.build());

    return new OAuth2AccessTokenAuthenticationToken(
      client, clientAuthenticationToken, accessToken, null, Maps.newHashMap()
    );
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return OAuth2PasswordAuthenticationToken.class.isAssignableFrom(authentication);
  }

  /**
   * 生成AccessToken
   *
   * @param tokenContextBuilder  token上下文建造者
   * @param authorizationBuilder 授权建造者
   * @return AccessToken
   */
  protected OAuth2AccessToken accessToken(
    DefaultOAuth2TokenContext.Builder tokenContextBuilder,
    OAuth2Authorization.Builder authorizationBuilder
  ) {
    OAuth2TokenContext tokenContext = tokenContextBuilder.tokenType(OAuth2TokenType.ACCESS_TOKEN).build();
    OAuth2Token generatedAccessToken = this.tokenGenerator.generate(tokenContext);
    if (generatedAccessToken == null) {
      throw new OAuth2AuthenticationException(
        new OAuth2Error(
          OAuth2ErrorCodes.SERVER_ERROR,
          "The token generator failed to generate the access token.",
          OAuth2EndpointUtils.ACCESS_TOKEN_REQUEST_ERROR_URI
        )
      );
    }
    OAuth2AccessToken result = new OAuth2AccessToken(
      OAuth2AccessToken.TokenType.BEARER,
      generatedAccessToken.getTokenValue(),
      generatedAccessToken.getIssuedAt(),
      generatedAccessToken.getExpiresAt(),
      tokenContext.getAuthorizedScopes()
    );
    if (generatedAccessToken instanceof ClaimAccessor) {
      authorizationBuilder.token(result, (metadata) ->
        metadata.put(
          OAuth2Authorization.Token.CLAIMS_METADATA_NAME,
          ((ClaimAccessor) generatedAccessToken).getClaims()
        )
      );
    } else {
      authorizationBuilder.accessToken(result);
    }
    return result;
  }

  /**
   * 获取用户认证授权或抛出异常
   *
   * @param token OAuth2密码授权Token
   * @return 用户认证授权
   */
  protected Authentication getUserAuthenticationElseThrowException(OAuth2PasswordAuthenticationToken token) {
    Authentication result = authenticationManager.authenticate(
      new UsernamePasswordAuthenticationToken(token.getUsername(), token.getPassword())
    );
    if (!result.isAuthenticated()) {
      throw new OAuth2AuthenticationException(
        new OAuth2Error(OAuth2ErrorCodes.INVALID_REQUEST, "用户认证失败", "")
      );
    }
    return result;
  }
}
