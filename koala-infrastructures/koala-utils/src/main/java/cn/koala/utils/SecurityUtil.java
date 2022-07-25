package cn.koala.utils;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * Java安全工具类
 *
 * @author Houtaroy
 */
public final class SecurityUtil {

  private SecurityUtil() {
    throw new IllegalStateException("工具类不允许实例化");
  }

  /**
   * 从base64解析rsa密钥对
   *
   * @param publicKey  公钥base64
   * @param privateKey 私钥base64
   * @return rsa密钥对
   * @throws NoSuchAlgorithmException NoSuchAlgorithmException
   * @throws InvalidKeySpecException  InvalidKeySpecException
   */
  public static KeyPair rsaKey(String publicKey, String privateKey) throws NoSuchAlgorithmException,
    InvalidKeySpecException {
    KeyFactory factory = KeyFactory.getInstance("RSA");
    return new KeyPair(
      factory.generatePublic(new X509EncodedKeySpec(Base64.getDecoder().decode(publicKey))),
      factory.generatePrivate(new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKey)))
    );
  }
}
