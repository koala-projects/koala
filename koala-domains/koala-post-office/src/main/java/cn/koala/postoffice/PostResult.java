package cn.koala.postoffice;

import lombok.Data;
import lombok.experimental.SuperBuilder;

/**
 * 投递结果
 *
 * @author Houtaroy
 */
@Data
@SuperBuilder(toBuilder = true)
public class PostResult {

  private String officeName;

  private Boolean isSuccess;

  private String error;

  public static PostResult success(String officeName) {
    return PostResult.builder()
      .officeName(officeName)
      .isSuccess(true)
      .build();
  }

  public static PostResult fail(String officeName, String error) {
    return PostResult.builder()
      .officeName(officeName)
      .isSuccess(false)
      .error(error)
      .build();
  }
}
