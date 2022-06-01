package cn.koala.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.jose.jws.SignatureAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.security.oauth2.server.authorization.OAuth2Authorization;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2AccessTokenAuthenticationToken;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;

import java.security.Principal;
import java.time.Instant;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Houtaroy
 */
@RequiredArgsConstructor
@SuppressWarnings("PMD")
public class OAuth2ResourceOwnerPasswordAuthenticationProvider implements AuthenticationProvider {
  protected final AuthenticationManager authenticationManager;
  protected final JwtEncoder jwtEncoder;
  protected final OAuth2AuthorizationService oAuth2AuthorizationService;

  @Override
  public OAuth2AccessTokenAuthenticationToken authenticate(Authentication authentication)
    throws AuthenticationException {
    OAuth2ResourceOwnerPasswordAuthenticationToken token =
      (OAuth2ResourceOwnerPasswordAuthenticationToken) authentication;
    RegisteredClient registeredClient = token.getRegisteredClientOrThrowException();
    Authentication userPrincipal = authenticationManager.authenticate(token.getUsernamePasswordAuthenticationToken());
    Set<String> authorities = getAuthorities(userPrincipal);
    Instant issuedAt = Instant.now();
    JwtClaimsSet.Builder claimBuilder = JwtClaimsSet.builder()
      .issuer("http://authorization-server:9001")
      .subject(userPrincipal.getName())
      .issuedAt(issuedAt)
      .expiresAt(issuedAt.plus(registeredClient.getTokenSettings().getAccessTokenTimeToLive()))
      .notBefore(issuedAt);

//    JwtEncodingContext context = JwtEncodingContext
//      .with(JwsHeader.with(SignatureAlgorithm.RS256), claimBuilder)
//      .registeredClient(registeredClient)
//      .principal(userPrincipal)
//      .authorizedScopes(authorities)
//      .tokenType(OAuth2TokenType.ACCESS_TOKEN)
//      .authorizationGrantType(AuthorizationGrantType.PASSWORD)
//      .authorizationGrant(token).build();

    Jwt jwt = jwtEncoder.encode(
      JwtEncoderParameters.from(JwsHeader.with(SignatureAlgorithm.RS256).build(), claimBuilder.build())
    );

    OAuth2AccessToken accessToken = new OAuth2AccessToken(
      OAuth2AccessToken.TokenType.BEARER,
      jwt.getTokenValue(),
      jwt.getIssuedAt(),
      jwt.getExpiresAt(),
      authorities
    );

    OAuth2Authorization authorization = OAuth2Authorization.withRegisteredClient(registeredClient)
      .principalName(userPrincipal.getName())
      .authorizationGrantType(AuthorizationGrantType.PASSWORD)
      .token(accessToken, (metadata) -> metadata.put(OAuth2Authorization.Token.CLAIMS_METADATA_NAME, jwt.getClaims()))
      .attribute(OAuth2Authorization.AUTHORIZED_SCOPE_ATTRIBUTE_NAME, authorities)
      .attribute(Principal.class.getName(), userPrincipal)
      .build();

    oAuth2AuthorizationService.save(authorization);

    return new OAuth2AccessTokenAuthenticationToken(registeredClient, userPrincipal, accessToken);
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return OAuth2ResourceOwnerPasswordAuthenticationToken.class.isAssignableFrom(authentication);
  }

  protected Set<String> getAuthorities(Authentication authentication) {
    return authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toSet());
  }
}
