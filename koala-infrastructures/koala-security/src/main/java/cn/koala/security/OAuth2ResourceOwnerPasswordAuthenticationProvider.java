package cn.koala.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.OAuth2TokenType;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.oauth2.jose.jws.SignatureAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.security.oauth2.server.authorization.OAuth2Authorization;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2AccessTokenAuthenticationToken;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2ClientAuthenticationToken;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer;

import java.security.Principal;
import java.time.Instant;
import java.util.Set;

/**
 * 密码模式认证信息提供者
 *
 * @author Houtaroy
 */
@RequiredArgsConstructor
@SuppressWarnings("PMD")
public class OAuth2ResourceOwnerPasswordAuthenticationProvider implements AuthenticationProvider {
  protected final AuthenticationManager authenticationManager;
  protected final JwtEncoder jwtEncoder;
  protected final OAuth2TokenCustomizer<JwtEncodingContext> customizer;
  protected final OAuth2AuthorizationService oAuth2AuthorizationService;
  protected final ProviderProperties providerProperties;

  @Override
  public OAuth2AccessTokenAuthenticationToken authenticate(Authentication authentication)
    throws AuthenticationException {
    OAuth2ResourceOwnerPasswordAuthenticationToken resourceOwnerPasswordAuthenticationToken =
      (OAuth2ResourceOwnerPasswordAuthenticationToken) authentication;
    RegisteredClient registeredClient = OAuth2Helper.getRegisteredClientElseThrowInvalidClient(
      (OAuth2ClientAuthenticationToken) authentication.getPrincipal()
    );

    Authentication userPrincipal = authenticationManager.authenticate(
      resourceOwnerPasswordAuthenticationToken.getUsernamePasswordAuthenticationToken()
    );

    JwtEncodingContext context = tokenContext(
      userPrincipal,
      registeredClient,
      resourceOwnerPasswordAuthenticationToken
    );

    Jwt jwt = jwtEncoder.encode(JwtEncoderParameters.from(context.getHeaders().build(), context.getClaims().build()));

    OAuth2AccessToken accessToken = new OAuth2AccessToken(
      OAuth2AccessToken.TokenType.BEARER,
      jwt.getTokenValue(),
      jwt.getIssuedAt(),
      jwt.getExpiresAt(),
      context.getAuthorizedScopes()
    );

    OAuth2Authorization.Builder authorizationBuilder = OAuth2Authorization.withRegisteredClient(registeredClient)
      .principalName(userPrincipal.getName())
      .authorizationGrantType(AuthorizationGrantType.PASSWORD)
      .token(accessToken, (metadata) -> metadata.put(OAuth2Authorization.Token.CLAIMS_METADATA_NAME, jwt.getClaims()))
      .attribute(OAuth2Authorization.AUTHORIZED_SCOPE_ATTRIBUTE_NAME, context.getAuthorizedScopes())
      .attribute(Principal.class.getName(), userPrincipal);

    oAuth2AuthorizationService.save(authorizationBuilder.build());

    return new OAuth2AccessTokenAuthenticationToken(registeredClient, userPrincipal, accessToken);
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return OAuth2ResourceOwnerPasswordAuthenticationToken.class.isAssignableFrom(authentication);
  }

  protected JwtEncodingContext tokenContext(Authentication principal, RegisteredClient registeredClient,
                                            OAuth2ResourceOwnerPasswordAuthenticationToken authenticationToken) {
    Set<String> authorities = OAuth2Helper.getAuthorities(principal);
    JwsHeader.Builder headerBuilder = JwsHeader.with(SignatureAlgorithm.RS256);
    Instant issuedAt = Instant.now();
    JwtClaimsSet.Builder claimsBuilder = JwtClaimsSet.builder().issuer(providerProperties.getIssuer())
      .subject(principal.getName())
      .issuedAt(issuedAt)
      .expiresAt(issuedAt.plus(registeredClient.getTokenSettings().getAccessTokenTimeToLive()))
      .notBefore(issuedAt)
      .claim(OAuth2ParameterNames.SCOPE, authorities);
    JwtEncodingContext result = JwtEncodingContext.with(headerBuilder, claimsBuilder)
      .registeredClient(registeredClient)
      .principal(principal)
      .authorizedScopes(authorities)
      .tokenType(OAuth2TokenType.ACCESS_TOKEN)
      .authorizationGrantType(AuthorizationGrantType.PASSWORD)
      .authorizationGrant(authenticationToken)
      .build();
    customizer.customize(result);
    return result;
  }
}
