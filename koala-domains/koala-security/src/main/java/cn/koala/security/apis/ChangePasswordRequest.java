package cn.koala.security.apis;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 修改密码请求
 *
 * @author Houtaroy
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "修改密码请求实体")
public class ChangePasswordRequest {
  @Schema(description = "当前密码", requiredMode = Schema.RequiredMode.REQUIRED)
  private String password;
  @Schema(description = "新密码", requiredMode = Schema.RequiredMode.REQUIRED)
  private String newPassword;
}
