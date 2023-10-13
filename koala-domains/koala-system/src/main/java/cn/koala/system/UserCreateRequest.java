package cn.koala.system;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.EntityListeners;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 创建用户请求
 *
 * @author Houtaroy
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Schema(description = "创建用户请求")
@EntityListeners(UserCreateListener.class)
public class UserCreateRequest extends UserEntity {

  @Schema(description = "明文密码")
  @NotBlank(message = "{user.password.not-blank}")
  @Size(min = 6, max = 20, message = "{user.password.size}")
  private String plainPassword;
}
