package cn.koala.system;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.security.Principal;

/**
 * @author Houtaroy
 */
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder(toBuilder = true)
public class ChangePasswordDTO extends ChangePasswordRequest {
  private String username;

  /**
   * 依据修改密码请求创建
   *
   * @param principal 用户主体
   * @param request   修改密码请求对象
   * @return 修改密码数据传输对象
   */
  public static ChangePasswordDTO from(Principal principal, ChangePasswordRequest request) {
    return ChangePasswordDTO.builder()
      .username(principal.getName())
      .passwordOld(request.getPasswordOld())
      .passwordNew(request.getPasswordNew())
      .build();
  }
}
