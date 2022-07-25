package cn.koala.security;

import cn.koala.utils.SecurityUtil;
import com.nimbusds.jose.jwk.RSAKey;

import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;

/**
 * Jwks
 *
 * @author Houtaroy
 */
@SuppressWarnings("PMD")
public final class Jwks {
  private Jwks() {
  }

  /**
   * 生成rsa密钥
   *
   * @param keyID      keyID
   * @param publicKey  公钥base64
   * @param privateKey 私钥base64
   * @return rsa密钥
   * @throws NoSuchAlgorithmException NoSuchAlgorithmException
   * @throws InvalidKeySpecException  InvalidKeySpecException
   */
  public static RSAKey generateRsa(String keyID, String publicKey, String privateKey)
    throws NoSuchAlgorithmException, InvalidKeySpecException {
    KeyPair keyPair = SecurityUtil.rsaKey(publicKey, privateKey);
    return new RSAKey.Builder((RSAPublicKey) keyPair.getPublic())
      .privateKey(keyPair.getPrivate())
      .keyID(keyID)
      .build();
  }
}
