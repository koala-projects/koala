/*
 * Copyright 2006-2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package cn.koala.security;

import org.bouncycastle.asn1.ASN1Sequence;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.security.spec.RSAPrivateCrtKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Reads RSA key pairs using BC provider classes but without the
 * need to specify a crypto provider or have BC added as one.
 *
 * @author Luke Taylor
 */
@SuppressWarnings("PMD")
public class RsaKeyUtil {
  private static final String BEGIN = "-----BEGIN";
  private static final Pattern PEM_DATA = Pattern.compile(
    "-----BEGIN (.*)-----(.*)-----END (.*)-----", Pattern.DOTALL
  );
  public static final int ASN1_SEQUENCE_SIZE = 9;
  public static final int ENC_KEY_GROUP = 2;
  public static final int ALG_GROUP = 1;
  public static final byte[] SSH_KEY_PREFIX = {0, 0, 0, 7, 's', 's', 'h', '-', 'r', 's', 'a'};
  public static final int BIG_INTEGER_BYTE_LENGTH = 4;
  public static final int INT_24 = 24;
  public static final int INT_16 = 16;
  public static final int INT_8 = 8;
  public static final int INT_3 = 3;
  public static final int INT_2 = 2;
  public static final String RSA_PRIVATE_KEY = "RSA PRIVATE KEY";
  public static final String PUBLIC_KEY = "PUBLIC KEY";
  public static final String RSA_PUBLIC_KEY = "RSA PUBLIC KEY";
  public static final String ALG_RSA = "rsa";

  /**
   * 解析密钥对
   *
   * @param pemData 证书内容
   * @return 密钥对
   */
  public static KeyPair parseKeyPair(String pemData) {
    Matcher m = PEM_DATA.matcher(pemData.trim());
    if (!m.matches()) {
      throw new IllegalArgumentException("String is not PEM encoded data");
    }
    String type = m.group(1);
    final byte[] content = Base64.getDecoder().decode(m.group(2));
    PublicKey publicKey;
    PrivateKey privateKey = null;
    try {
      KeyFactory fact = KeyFactory.getInstance("RSA");
      if (RSA_PRIVATE_KEY.equals(type)) {
        ASN1Sequence seq = ASN1Sequence.getInstance(content);
        if (seq.size() != ASN1_SEQUENCE_SIZE) {
          throw new IllegalArgumentException("Invalid RSA Private Key ASN1 sequence.");
        }
        org.bouncycastle.asn1.pkcs.RSAPrivateKey key = org.bouncycastle.asn1.pkcs.RSAPrivateKey.getInstance(seq);
        RSAPublicKeySpec pubSpec = new RSAPublicKeySpec(key.getModulus(), key.getPublicExponent());
        RSAPrivateCrtKeySpec privSpec = new RSAPrivateCrtKeySpec(key.getModulus(), key.getPublicExponent(),
          key.getPrivateExponent(), key.getPrime1(), key.getPrime2(), key.getExponent1(), key.getExponent2(),
          key.getCoefficient());
        publicKey = fact.generatePublic(pubSpec);
        privateKey = fact.generatePrivate(privSpec);
      } else if (PUBLIC_KEY.equals(type)) {
        KeySpec keySpec = new X509EncodedKeySpec(content);
        publicKey = fact.generatePublic(keySpec);
      } else if (RSA_PUBLIC_KEY.equals(type)) {
        ASN1Sequence seq = ASN1Sequence.getInstance(content);
        org.bouncycastle.asn1.pkcs.RSAPublicKey key = org.bouncycastle.asn1.pkcs.RSAPublicKey.getInstance(seq);
        RSAPublicKeySpec pubSpec = new RSAPublicKeySpec(key.getModulus(), key.getPublicExponent());
        publicKey = fact.generatePublic(pubSpec);
      } else {
        throw new IllegalArgumentException(type + " is not a supported format");
      }
      return new KeyPair(publicKey, privateKey);
    } catch (InvalidKeySpecException e) {
      throw new RuntimeException(e);
    } catch (NoSuchAlgorithmException e) {
      throw new IllegalStateException(e);
    }
  }

  private static final Pattern SSH_PUB_KEY = Pattern.compile("ssh-(rsa|dsa) ([A-Za-z0-9/+]+=*) (.*)");

  /**
   * 解析公钥
   *
   * @param key 证书内容
   * @return 公钥
   */
  public static RSAPublicKey parsePublicKey(String key) {
    Matcher m = SSH_PUB_KEY.matcher(key);

    if (m.matches()) {
      String alg = m.group(ALG_GROUP);
      String encKey = m.group(ENC_KEY_GROUP);
      //String id = m.group(3);

      if (!ALG_RSA.equalsIgnoreCase(alg)) {
        throw new IllegalArgumentException("Only RSA is currently supported, but algorithm was " + alg);
      }

      return parseSSHPublicKey(encKey);
    } else if (!key.startsWith(BEGIN)) {
      // Assume it's the plain Base64 encoded ssh key without the "ssh-rsa" at the start
      return parseSSHPublicKey(key);
    }

    KeyPair kp = parseKeyPair(key);

    if (kp.getPublic() == null) {
      throw new IllegalArgumentException("Key data does not contain a public key");
    }

    return (RSAPublicKey) kp.getPublic();
  }

  /**
   * 解析SSH公钥
   *
   * @param encKey 证书
   * @return 公钥
   */
  private static RSAPublicKey parseSSHPublicKey(String encKey) {
    ByteArrayInputStream in = new ByteArrayInputStream(
      Base64.getDecoder().decode(encKey)
    );

    byte[] prefix = new byte[SSH_KEY_PREFIX.length];

    try {
      if (in.read(prefix) != SSH_KEY_PREFIX.length || !Arrays.equals(SSH_KEY_PREFIX, prefix)) {
        throw new IllegalArgumentException("SSH key prefix not found");
      }

      BigInteger e = new BigInteger(readBigInteger(in));
      BigInteger n = new BigInteger(readBigInteger(in));

      return createPublicKey(n, e);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * 创建公钥
   *
   * @param n the modulus
   * @param e the public exponent
   * @return 公钥
   */
  public static RSAPublicKey createPublicKey(BigInteger n, BigInteger e) {
    try {
      return (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(new RSAPublicKeySpec(n, e));
    } catch (Exception ex) {
      throw new RuntimeException(ex);
    }
  }

  /**
   * 读取打整数
   *
   * @param in 字节流
   * @return 包含整数的数组
   * @throws IOException IO异常
   */
  private static byte[] readBigInteger(ByteArrayInputStream in) throws IOException {
    byte[] b = new byte[BIG_INTEGER_BYTE_LENGTH];

    if (in.read(b) != BIG_INTEGER_BYTE_LENGTH) {
      throw new IOException("Expected length data as 4 bytes");
    }

    int l = (b[0] << INT_24) | (b[1] << INT_16) | (b[INT_2] << INT_8) | b[INT_3];

    b = new byte[l];

    if (in.read(b) != l) {
      throw new IOException("Expected " + l + " key bytes");
    }

    return b;
  }
}
