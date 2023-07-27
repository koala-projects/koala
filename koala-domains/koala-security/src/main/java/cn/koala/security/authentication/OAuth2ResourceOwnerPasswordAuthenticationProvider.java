package cn.koala.security.authentication;

import lombok.RequiredArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClaimAccessor;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.OAuth2ErrorCodes;
import org.springframework.security.oauth2.core.OAuth2RefreshToken;
import org.springframework.security.oauth2.core.OAuth2Token;
import org.springframework.security.oauth2.server.authorization.OAuth2Authorization;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.OAuth2TokenType;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2AccessTokenAuthenticationToken;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2ClientAuthenticationToken;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.context.AuthorizationServerContextHolder;
import org.springframework.security.oauth2.server.authorization.token.DefaultOAuth2TokenContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenGenerator;

import java.security.Principal;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class OAuth2ResourceOwnerPasswordAuthenticationProvider implements AuthenticationProvider {
  private static final String ERROR_URI = "https://datatracker.ietf.org/doc/html/rfc6749#section-5.2";
  private final Log logger = LogFactory.getLog(getClass());
  private final AuthenticationManager authenticationManager;
  private final OAuth2AuthorizationService authorizationService;
  private final OAuth2TokenGenerator<? extends OAuth2Token> tokenGenerator;

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    OAuth2ResourceOwnerPasswordAuthenticationToken resourceOwnerPasswordAuthentication = (OAuth2ResourceOwnerPasswordAuthenticationToken) authentication;

    OAuth2ClientAuthenticationToken clientPrincipal = getAuthenticatedClientElseThrowInvalidClient(resourceOwnerPasswordAuthentication);

    RegisteredClient registeredClient = clientPrincipal.getRegisteredClient();

    if (this.logger.isTraceEnabled()) {
      this.logger.trace("Retrieved registered client");
    }

    if (registeredClient == null) {
      throw new OAuth2AuthenticationException(OAuth2ErrorCodes.INVALID_CLIENT);
    }

    if (!registeredClient.getAuthorizationGrantTypes().contains(AuthorizationGrantType.PASSWORD)) {
      throw new OAuth2AuthenticationException(OAuth2ErrorCodes.UNSUPPORTED_GRANT_TYPE);
    }

    Authentication usernamePasswordAuthentication = authenticationManager.authenticate(
      new UsernamePasswordAuthenticationToken(
        resourceOwnerPasswordAuthentication.getUsername(),
        resourceOwnerPasswordAuthentication.getPassword()
      )
    );

    if (!usernamePasswordAuthentication.isAuthenticated()) {
      throw new OAuth2AuthenticationException("用户认证失败");
    }

    Set<String> scopes = usernamePasswordAuthentication.getAuthorities().stream()
      .map(GrantedAuthority::getAuthority)
      .collect(Collectors.toSet());

    // @formatter:off
    DefaultOAuth2TokenContext.Builder tokenContextBuilder = DefaultOAuth2TokenContext.builder()
        .registeredClient(registeredClient)
        .principal(usernamePasswordAuthentication)
        .authorizationServerContext(AuthorizationServerContextHolder.getContext())
        .authorizedScopes(scopes)
        .authorizationGrantType(AuthorizationGrantType.PASSWORD)
        .authorizationGrant(resourceOwnerPasswordAuthentication);
    // @formatter:on

    // ----- Access token -----
    OAuth2TokenContext tokenContext = tokenContextBuilder.tokenType(OAuth2TokenType.ACCESS_TOKEN).build();
    OAuth2Token generatedAccessToken = this.tokenGenerator.generate(tokenContext);
    if (generatedAccessToken == null) {
      OAuth2Error error = new OAuth2Error(OAuth2ErrorCodes.SERVER_ERROR,
        "The token generator failed to generate the access token.", ERROR_URI);
      throw new OAuth2AuthenticationException(error);
    }

    if (this.logger.isTraceEnabled()) {
      this.logger.trace("Generated access token");
    }

    OAuth2AccessToken accessToken = new OAuth2AccessToken(OAuth2AccessToken.TokenType.BEARER,
      generatedAccessToken.getTokenValue(), generatedAccessToken.getIssuedAt(),
      generatedAccessToken.getExpiresAt(), tokenContext.getAuthorizedScopes());

    // @formatter:off
    OAuth2Authorization.Builder authorizationBuilder = OAuth2Authorization.withRegisteredClient(registeredClient)
      .principalName(usernamePasswordAuthentication.getName())
      .authorizationGrantType(AuthorizationGrantType.PASSWORD)
      .authorizedScopes(scopes)
      .attribute(Principal.class.getName(), usernamePasswordAuthentication);
    // @formatter:on

    if (generatedAccessToken instanceof ClaimAccessor) {
      authorizationBuilder.token(accessToken, (metadata) ->
        metadata.put(OAuth2Authorization.Token.CLAIMS_METADATA_NAME, ((ClaimAccessor) generatedAccessToken).getClaims()));
    } else {
      authorizationBuilder.accessToken(accessToken);
    }

    // ----- Refresh token -----
    OAuth2RefreshToken refreshToken = null;
    if (registeredClient.getAuthorizationGrantTypes().contains(AuthorizationGrantType.REFRESH_TOKEN) &&
      // Do not issue refresh token to public client
      !clientPrincipal.getClientAuthenticationMethod().equals(ClientAuthenticationMethod.NONE)) {

      tokenContext = tokenContextBuilder.tokenType(OAuth2TokenType.REFRESH_TOKEN).build();
      OAuth2Token generatedRefreshToken = this.tokenGenerator.generate(tokenContext);
      if (!(generatedRefreshToken instanceof OAuth2RefreshToken)) {
        OAuth2Error error = new OAuth2Error(OAuth2ErrorCodes.SERVER_ERROR,
          "The token generator failed to generate the refresh token.", ERROR_URI);
        throw new OAuth2AuthenticationException(error);
      }

      if (this.logger.isTraceEnabled()) {
        this.logger.trace("Generated refresh token");
      }

      refreshToken = (OAuth2RefreshToken) generatedRefreshToken;
      authorizationBuilder.refreshToken(refreshToken);
    }

    // ----- ID token -----
    // OidcIdToken idToken;
    // OAuth2AuthorizationRequest authorizationRequest = authentication.getAttribute(
    //   OAuth2AuthorizationRequest.class.getName());
    // if (authorizationRequest.getScopes().contains(OidcScopes.OPENID)) {
    //   // @formatter:off
    //   tokenContext = tokenContextBuilder
    //       .tokenType(ID_TOKEN_TOKEN_TYPE)
    //       .authorization(authorizationBuilder.build())  // ID token customizer may need access to the access token and/or refresh token
    //       .build();
    //   // @formatter:on
    //   OAuth2Token generatedIdToken = this.tokenGenerator.generate(tokenContext);
    //   if (!(generatedIdToken instanceof Jwt)) {
    //     OAuth2Error error = new OAuth2Error(OAuth2ErrorCodes.SERVER_ERROR,
    //       "The token generator failed to generate the ID token.", ERROR_URI);
    //     throw new OAuth2AuthenticationException(error);
    //   }
    //
    //   if (this.logger.isTraceEnabled()) {
    //     this.logger.trace("Generated id token");
    //   }
    //
    //   idToken = new OidcIdToken(generatedIdToken.getTokenValue(), generatedIdToken.getIssuedAt(),
    //     generatedIdToken.getExpiresAt(), ((Jwt) generatedIdToken).getClaims());
    //   authorizationBuilder.token(idToken, (metadata) ->
    //     metadata.put(OAuth2Authorization.Token.CLAIMS_METADATA_NAME, idToken.getClaims()));
    // } else {
    //   idToken = null;
    // }

    OAuth2Authorization authorization = authorizationBuilder.build();

    this.authorizationService.save(authorization);

    if (this.logger.isTraceEnabled()) {
      this.logger.trace("Saved authorization");
    }

    Map<String, Object> additionalParameters = Collections.emptyMap();
    // if (idToken != null) {
    //   additionalParameters = new HashMap<>();
    //   additionalParameters.put(OidcParameterNames.ID_TOKEN, idToken.getTokenValue());
    // }

    if (this.logger.isTraceEnabled()) {
      this.logger.trace("Authenticated token request");
    }

    return new OAuth2AccessTokenAuthenticationToken(
      registeredClient, clientPrincipal, accessToken, refreshToken, additionalParameters);
  }

  protected OAuth2ClientAuthenticationToken getAuthenticatedClientElseThrowInvalidClient(Authentication authentication) {
    return Optional.ofNullable(authentication)
      .map(Authentication::getPrincipal)
      .filter(principal -> OAuth2ClientAuthenticationToken.class.isAssignableFrom(principal.getClass()))
      .map(OAuth2ClientAuthenticationToken.class::cast)
      .filter(AbstractAuthenticationToken::isAuthenticated)
      .orElseThrow(() -> new OAuth2AuthenticationException(OAuth2ErrorCodes.INVALID_CLIENT));
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return OAuth2ResourceOwnerPasswordAuthenticationToken.class.isAssignableFrom(authentication);
  }
}
