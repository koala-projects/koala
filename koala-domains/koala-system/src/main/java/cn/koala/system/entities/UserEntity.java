package cn.koala.system.entities;

import cn.koala.mybatis.AbstractEntity;
import cn.koala.persist.validator.UniqueField;
import cn.koala.system.User;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
@UniqueField(value = "username", message = "{user.username.unique}")
@UniqueField(value = "nickname", message = "{user.nickname.unique}")
@Schema(description = "用户数据实体")
public class UserEntity extends AbstractEntity<Long> implements User {

  @Schema(description = "登录名")
  @NotBlank(message = "{user.username.not-blank}")
  @Size(min = 4, max = 20, message = "{user.username.size}")
  String username;

  @Schema(description = "密码")
  String password;

  @Schema(description = "昵称")
  @NotBlank(message = "{user.nickname.not-blank}")
  @Size(min = 2, max = 20, message = "{user.nickname.size}")
  String nickname;

  @Schema(description = "头像")
  String avatar;

  @Schema(description = "邮箱")
  String email;

  @Schema(description = "手机")
  String mobile;

  @Schema(description = "备注")
  @Size(max = 100, message = "{user.remark.size}")
  String remark;
}
