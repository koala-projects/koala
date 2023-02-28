package cn.koala.system.entities;

import cn.koala.system.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * 用户数据实体类
 *
 * @author Houtaroy
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@Schema(description = "用户数据实体")
public class UserEntity extends BaseSystemEntity implements User {
  @Schema(description = "登录名")
  String username;
  @Schema(description = "密码")
  String password;
  @Schema(description = "昵称")
  String nickname;
  @Schema(description = "头像")
  String avatar;
  @Schema(description = "邮箱")
  String email;
  @Schema(description = "手机")
  String mobile;
  @Schema(description = "备注")
  String remark;
}
