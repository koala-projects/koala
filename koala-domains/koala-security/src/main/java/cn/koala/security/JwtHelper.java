package cn.koala.security;

import com.nimbusds.jose.jwk.RSAKey;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * RSA Key 帮助类
 *
 * @author Houtaroy
 */
public abstract class JwtHelper {
  public static RSAKey generateRsa(String keyID, String publicKey, String privateKey)
    throws NoSuchAlgorithmException, InvalidKeySpecException {
    KeyPair keyPair = rsa(publicKey, privateKey);
    return new RSAKey.Builder((RSAPublicKey) keyPair.getPublic())
      .privateKey(keyPair.getPrivate())
      .keyID(keyID)
      .build();
  }

  private static KeyPair rsa(String publicKey, String privateKey) throws NoSuchAlgorithmException,
    InvalidKeySpecException {
    KeyFactory factory = KeyFactory.getInstance("RSA");
    return new KeyPair(
      factory.generatePublic(new X509EncodedKeySpec(Base64.getDecoder().decode(publicKey))),
      factory.generatePrivate(new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKey)))
    );
  }
}
