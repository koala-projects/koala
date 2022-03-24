package cn.houtaroy.koala.starter.oauth2;

import com.nimbusds.jose.jwk.Curve;
import com.nimbusds.jose.jwk.ECKey;
import com.nimbusds.jose.jwk.OctetSequenceKey;
import com.nimbusds.jose.jwk.RSAKey;

import javax.crypto.SecretKey;
import java.security.KeyPair;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.UUID;

/**
 * @author Houtaroy
 */
public final class Jwks {

  private Jwks() {

  }

  /**
   * 生成RSAKey
   *
   * @return RSAKey
   */
  public static RSAKey generateRsa() {
    KeyPair keyPair = KeyGeneratorUtils.generateRsaKey();
    RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
    RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
    // @formatter:off
    return new RSAKey.Builder(publicKey)
      .privateKey(privateKey)
      .keyID(UUID.randomUUID().toString())
      .build();
    // @formatter:on
  }

  /**
   * 生成ECKey
   *
   * @return ECKey
   */
  public static ECKey generateEc() {
    KeyPair keyPair = KeyGeneratorUtils.generateEcKey();
    ECPublicKey publicKey = (ECPublicKey) keyPair.getPublic();
    ECPrivateKey privateKey = (ECPrivateKey) keyPair.getPrivate();
    Curve curve = Curve.forECParameterSpec(publicKey.getParams());
    return new ECKey.Builder(curve, publicKey)
      .privateKey(privateKey)
      .keyID(UUID.randomUUID().toString())
      .build();
  }

  /**
   * 生成OctetSequenceKey
   *
   * @return OctetSequenceKey
   */
  public static OctetSequenceKey generateSecret() {
    SecretKey secretKey = KeyGeneratorUtils.generateSecretKey();
    return new OctetSequenceKey.Builder(secretKey)
      .keyID(UUID.randomUUID().toString())
      .build();
  }
}
