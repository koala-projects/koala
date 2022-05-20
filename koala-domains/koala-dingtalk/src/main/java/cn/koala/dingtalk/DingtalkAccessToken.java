package cn.koala.dingtalk;

import com.aliyun.dingtalkoauth2_1_0.models.GetAccessTokenResponseBody;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author Houtaroy
 */
@Data
public class DingtalkAccessToken {
  public static final int TOKEN_RESERVED_SECONDS = 300;
  private String value;
  private LocalDateTime expireTime;

  /**
   * 构造方法
   */
  public DingtalkAccessToken() {
    this.value = "";
    this.expireTime = LocalDateTime.now();
  }

  /**
   * 构造函数
   *
   * @param body AccessToken请求结果
   */
  public DingtalkAccessToken(GetAccessTokenResponseBody body) {
    this.value = body.getAccessToken();
    this.expireTime = LocalDateTime.now().plusSeconds(body.getExpireIn() - TOKEN_RESERVED_SECONDS);
  }

  /**
   * 是否过期
   *
   * @return true:过期
   */
  public boolean isExpired() {
    return LocalDateTime.now().isAfter(expireTime);
  }
}
