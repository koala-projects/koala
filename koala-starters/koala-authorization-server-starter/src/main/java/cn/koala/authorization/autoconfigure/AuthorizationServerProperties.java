package cn.koala.authorization.autoconfigure;

import com.nimbusds.jose.jwk.JWKSet;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * 认证授权服务配置属性
 *
 * @author Houtaroy
 */
@ConfigurationProperties(prefix = "koala.security.authorization-server")
@Data
public class AuthorizationServerProperties {

  private boolean customLoginPage = false;

  private GrantType grantType = new GrantType();

  private Jwk jwk = new Jwk();

  @Data
  @NoArgsConstructor
  public static class GrantType {
    private boolean password = false;
  }

  @Data
  @NoArgsConstructor
  public static class Jwk {

    private String keyID = "koala";
    private String publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAtdDfQW74SVx+MPhiCOqmh7wtWoRzjkzzFSHMDNLXN+fRvhEsn04+bwxxS7oycgkceIdl0VgDaX0SXOk5P9kx6l6JIFWQoPz1zqC24v0VI6W003mo0I9QwmPMYCfLP6d39E52XLo4PYyGrebROVVFbBtP8elgmvl7Ya9LpFEAIJ2+LDleK5B0Nc8+WmQdSDnodMvNJS+hp+BKMixkLvor886HJ3z0ci8/GQg1bfTTL0aKTF+1wlL0CmPB17m2FOQrNccWsF8vjfRVcSaTf4GVHxVxn2Qm9lIZj5nQQ0Pp2yQPwwXLiRKrMSZpzrI+GzTNNgK51WwlncTdMgjBYXSCYwIDAQAB";
    private String privateKey = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQC10N9BbvhJXH4w+GII6qaHvC1ahHOOTPMVIcwM0tc359G+ESyfTj5vDHFLujJyCRx4h2XRWANpfRJc6Tk/2THqXokgVZCg/PXOoLbi/RUjpbTTeajQj1DCY8xgJ8s/p3f0TnZcujg9jIat5tE5VUVsG0/x6WCa+Xthr0ukUQAgnb4sOV4rkHQ1zz5aZB1IOeh0y80lL6Gn4EoyLGQu+ivzzocnfPRyLz8ZCDVt9NMvRopMX7XCUvQKY8HXubYU5Cs1xxawXy+N9FVxJpN/gZUfFXGfZCb2UhmPmdBDQ+nbJA/DBcuJEqsxJmnOsj4bNM02ArnVbCWdxN0yCMFhdIJjAgMBAAECggEAKHT06MN/nhtlvRHYBrHoInX+BZFJgVWgFYiytfvIhhkgc9GwgzZO8DjtkPM6vGNIoY1HW8Dg6X06M9B1u75hvAwTKU3A5AHF1JewemvkY01BInmE1fu99bNtS8wvDtf9+i/naJTLZO67HUEqNz7BNuAQ9vx+UZ3IFaGDDGo1xAvVVOhhQuWFRtP/rXfJxd8nZwFGdsxEsIO4AkMS3Xvl73VbO1Xm94wNvZW3348dTxLbRNaTWG/SSXgVQxRHZUwoZiBg+3Vvug4VspEtHwEKn8g3i7d2aWYb8Ev7Wb+QO8hythTBxW5t/sRS0MmplyZS0TeonV722u4VROy68Jq3SQKBgQDshAagGqCPO/hI7rvXr0LNXPqYByuF0awwkLOjXiK0ZgDx+m70E+XGl7pOIvhblkWgR3zReK8VY7tAUX8WGxQyznhanCXUTEw3vcO5TH7kxRoaZYpcIAW6G3dqPGXxb074YSlEv8ZTskMUDw0YW/ArnW9ozT3Sl5UZGto58MhtNQKBgQDEy0KOZnwiwybpUTG4iLX9na1nvmIADS15CnKAYVsjKYsJ0JMCC7EbyZ8e+LUlEd1cIUHk/VgyQkYm2f2Ptcc7wYHnmZRkTlof+K08rYoTv81BAL7F/pPDCMVz4diC3nPY5VZ7RGNdtOFyReUcLuHegFL2yGUFT7lopebAFU5cNwKBgCSa3mejF1xzvJ6VDE96WY1tKX+kBHFmnQ67JoJPTHG9I8JPryJpffN5giUbtnJ+VHJU/hVxZy+ZSt7OfivOrxaZ6/iiGBy+XkF2RjeEhlMjykFxm7NcZWaDX7z6chCm2IMKjpeMUsabXVRaa/1sJUSqzhZbRYk1WqcTB9lV0b9NAoGAG1o5DPvXoylGvkUnyQNPcEm7MWE309QBDwLRuWBOv4tlpN6VoEIpwrf6Yt6kpttdzhFdTEueN5YYlAvWf2P/piGWQa+J/YsaYbk5suZX+7i7+Z315iUlOoV8lEe+xSnQJkRyCb4OpZafgzeNyaYGhNuHLH/b8qoLFi2A7g/IDW8CgYEA17xdXX6257DSKgPy7QM1Y7PUsNT0cCRK+D4s52xb3KQ/N4IY9xSdSpYKvQLWbpf8gwLyxvAwWIyZaxvHjWnwFbF5fx4YXWflWOaH64W4pK9BCE6VQhugBLCDRYfovRoK7ys/LB+x+lHTB2D0lb5sGn+sfLOkj3uoHn7N3bHdkCE=";

    public JWKSet getJwkSet() throws NoSuchAlgorithmException, InvalidKeySpecException {
      return new JWKSet(JwtHelper.generateRsa(this.keyID, this.publicKey, this.privateKey));
    }
  }
}
