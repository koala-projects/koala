package cn.koala.system.domain;

import cn.koala.mybatis.domain.AbstractEntity;
import cn.koala.system.util.SystemConstants;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.EntityListeners;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.io.Serializable;

/**
 * 用户数据实体类
 *
 * @author Houtaroy
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@EntityListeners(UserEntityListener.class)
@Schema(description = "用户数据实体")
public class UserEntity extends AbstractEntity<Long, Long> implements User, Serializable {

  @Serial
  private static final long serialVersionUID = SystemConstants.SERIAL_VERSION_UID;

  @Schema(description = "用户登录名")
  @NotBlank(message = "用户登录名不能为空")
  @Size(min = 6, max = 20, message = "用户登录名长度必须在6-20之间")
  private String username;

  @Schema(description = "用户密码")
  private String password;

  @Schema(description = "用户昵称")
  @NotBlank(message = "用户昵称不能为空")
  @Size(min = 2, max = 20, message = "用户昵称长度必须在2-20之间")
  private String nickname;

  @Schema(description = "用户头像")
  private String avatar;

  @Schema(description = "用户邮箱")
  @Size(max = 50, message = "用户邮箱长度不能超过50")
  private String email;

  @Schema(description = "用户手机")
  @Size(max = 20, message = "用户手机长度不能超过20")
  private String mobile;

  @Schema(description = "用户描述")
  @Size(max = 200, message = "用户描述长度不能超过200")
  private String description;
}
