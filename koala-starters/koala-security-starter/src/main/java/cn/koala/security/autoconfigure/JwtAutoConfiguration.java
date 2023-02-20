package cn.koala.security.autoconfigure;

import cn.koala.security.JwtHelper;
import cn.koala.security.UserDetailsImpl;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.server.authorization.OAuth2TokenType;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashSet;

/**
 * Jwt自动配置类
 *
 * @author Houtaroy
 */
@RequiredArgsConstructor
public class JwtAutoConfiguration {
  private final JwtProperties properties;

  @Bean
  public JWKSource<SecurityContext> jwkSource()
    throws NoSuchAlgorithmException, InvalidKeySpecException {
    JWKSet jwkSet = new JWKSet(
      JwtHelper.generateRsa(properties.getKeyID(), properties.getPublicKey(), properties.getPrivateKey())
    );
    return (jwkSelector, securityContext) -> jwkSelector.select(jwkSet);
  }

  @Bean
  public JwtDecoder jwtDecoder(JWKSource<SecurityContext> jwkSource) {
    return OAuth2AuthorizationServerConfiguration.jwtDecoder(jwkSource);
  }

  @Bean
  public OAuth2TokenCustomizer<JwtEncodingContext> jwtCustomizer() {
    return context -> {
      JwtClaimsSet.Builder claims = context.getClaims();
      if (context.getTokenType().equals(OAuth2TokenType.ACCESS_TOKEN)) {
        if (context.getPrincipal().getPrincipal() instanceof UserDetailsImpl userDetails) {
          claims.claim("id", userDetails.getId().toString());
          claims.claim("username", userDetails.getUsername());
          claims.claim("nickname", userDetails.getNickname());
          claims.claim("scope", new HashSet<>(userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList()));
        }
      }
    };
  }
}
