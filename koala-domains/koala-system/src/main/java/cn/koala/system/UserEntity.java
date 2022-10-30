package cn.koala.system;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * 用户数据实体
 *
 * @author Houtaroy
 */
@Data
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@Schema(description = "用户数据实体")
public class UserEntity implements User {
  @Schema(description = "用户ID")
  private String id;
  @Schema(description = "用户名称")
  private String username;
  @Schema(description = "用户密码")
  private String password;
  @Schema(description = "用户昵称")
  private String nickname;
  @Schema(description = "用户头像")
  private String avatar;
}
